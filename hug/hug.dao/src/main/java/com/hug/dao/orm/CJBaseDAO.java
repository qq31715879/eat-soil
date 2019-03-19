package com.hug.dao.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.hug.core.data.structure.map.CJHashMap;
import com.hug.core.string.BZString;
import com.hug.dao.orm.CJType.JavaType;
import com.hugl.core.reflect.BZReflect;

/**
 * @author Kael
 */
public class CJBaseDAO<T> {
	private static final Log LOG = LogFactory.getLog(CJBaseDAO.class);
	protected Connection connection;
	/**
	 * class of entity
	 */
	protected Class<T> entityClass;
	protected String tableName;

	@SuppressWarnings("unchecked")
	private void initCJBaseDAO(String... tableName) {
		String daoClass = getClass().getName().toString();
		daoClass = daoClass.substring(0, daoClass.length() - 3);
		try {
			entityClass = (Class<T>) Class.forName(daoClass.replace(".dao", ".bean"));
		} catch (ClassNotFoundException e) {
//			LOG.error("Dao Class change Bean Class", e);
		}
		this.tableName = tableName.length == 0 ? getTableName() : tableName[0];
	}

	public CJBaseDAO(CJDB cjdb, String... tableName) {
		initCJBaseDAO(tableName);
		connection = CJDBPool.getConnection(cjdb);
	}

	public CJBaseDAO(Connection connection, String... tableName) {
		initCJBaseDAO(tableName);
		this.connection = connection;
	}

	public void destroy() {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.error("Close Connection", e);
			}
		}
	}

	/**********************************************************************************************************************************/
	/*******************************************************
	 * SQL SELECT
	 **************************************************************/
	/**********************************************************************************************************************************/

	/**
	 * print entity field by table
	 */
	public void selectColumnName(String tableSchema) {
		Map<String, JavaType> query = new HashMap<String, JavaType>();
		query.put("COLUMN_NAME", JavaType.STRING);
		query.put("COLUMN_DEFAULT", JavaType.STRING);
		query.put("COLUMN_COMMENT", JavaType.STRING);
		query.put("DATA_TYPE", JavaType.STRING);
		String sql = CJSql.selectFrom(query) + "information_schema.COLUMNS where table_name=? and table_schema=?";
		List<Map<String, Object>> list = CJDBExecute.queryForListMap(connection, query, sql, tableName, tableSchema);
		String name, type, comment;
		StringBuilder insert = new StringBuilder("insert into ").append(tableName).append(" (");
		List<String> names = new ArrayList<>();
		for (Map<String, Object> map : list) {
			name = map.get("COLUMN_NAME").toString();
			type = CJType.getJavaType(map.get("DATA_TYPE").toString());
			comment = map.get("COLUMN_COMMENT").toString();
			if (BZString.isNotNullOrEmpty(comment)) {
				System.out.println("/**" + comment + "*/");
			}
			insert.append(name).append(", ");
			names.add(BZString.underlineToHump(name));
			System.out.println("private " + type + " " + BZString.underlineToHump(name) + ";");
		}
		insert.append(") values (");
		for (String uname : names) {
			insert.append("#{" + uname + "}, ");
		}
		insert.append(")");
		System.out.println(insert.toString());
	}

	public List<Integer> selectCount(String sql, Object... params) {
		List<Integer> rows = new ArrayList<Integer>();
		List<Map<String, Object>> list = CJDBExecute.queryForListMap(connection,
				new CJHashMap<String, JavaType>("count", JavaType.INT), sql, params);
		String result = null;
		for (Map<String, Object> map : list) {
			result = map.get("count").toString().trim().toLowerCase();
			rows.add("null".equals(result) ? 0 : Integer.valueOf(result));
		}
		if (rows.size() == 0)
			rows.add(0);
		return rows;
	}

	public <O> List<O> selectAll(Class<O> entityClass) {
		return CJDBExecute.queryForList(connection, entityClass, CJSql.select(tableName));
	}

	public List<T> selectAll() {
		return CJDBExecute.queryForList(connection, entityClass, CJSql.select(tableName));
	}

	protected int selectLastInsertId() {
		return Integer
				.valueOf(CJDBExecute.queryForListMap(connection, new CJHashMap<String, JavaType>("id", JavaType.INT),
						"select last_insert_id() as id").get(0).get("id").toString());
	}

	/**********************************************************************************************************************************/
	/*******************************************************
	 * SQL INSERT
	 **************************************************************/
	/**********************************************************************************************************************************/

	public <O> int insert(O t) throws Exception {
		return insert(t, 0);
	}

	public <O> int insertNoID(O t) throws Exception {
		return insert(t, 1);
	}

	/**
	 * 
	 * @param insertId
	 *            0:ID 1:NO ID
	 * @return LastID
	 * @throws Exception
	 */
	private <O> int insert(O t, int insertId) throws Exception {
		Class<? extends Object> entityClass = BZReflect.getSuperClass(t.getClass());
		Field[] fields = entityClass.getDeclaredFields();
		ArrayList<String> params = new ArrayList<String>();
		ArrayList<Object> values = new ArrayList<Object>();
		String name = null;
		for (int i = 0 + insertId; i < fields.length; i++) {
			name = fields[i].getName();
			Object value = BZReflect.getEntityAttribute(t, name);
			if (null == value) {
				continue;
			}
			params.add(name);
			values.add(value);
		}
		return insert(params.toArray(), values.toArray());
	}

	public int insert(Object[] name, Object... values) {
		int count = CJDBExecute.executeUpdate(connection, CJSql.insert(tableName, name, values), values);
		return count > 0 ? selectLastInsertId() : count;
	}

	/**********************************************************************************************************************************/
	/********************* SQL DELETE *********************/
	/**********************************************************************************************************************************/
	public int delete(String... where) {
		String sql = CJSql.delete(tableName);
		return CJDBExecute.executeUpdate(connection, where.length == 0 ? sql : sql + where[0]);
	}

	/**
	 * get tableName by dao Class
	 */
	private final String getTableName() {
		String temp = this.getClass().getSimpleName().toLowerCase();
		return temp.substring(0, temp.length() - 3);
	}
}
