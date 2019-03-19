package com.hug.dao.orm;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.hug.dao.orm.CJType.JavaType;
import com.hugl.core.reflect.BZReflect;

/**
 * @author Kael
 */
public class CJDBExecute {
	private static final Log LOG = LogFactory.getLog(CJDBExecute.class);

	private CJDBExecute() {
	}

	/**
	 * Procedure DDL(Data Definition Language)—数据定义语言(CREATE，ALTER，DROP，DECLARE)
	 * DCL(Data Control Language)—数据控制语言(GRANT，REVOKE，COMMIT，ROLLBACK)
	 */
	public final static List<Map<String, Object>> call(Connection connection, HashMap<String, JavaType> query,
			String sql, Object... params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		try {
			callableStatement = callableStatement(connection, sql, params);
			callableStatement.execute();
			resultSet = callableStatement.getResultSet();
			list = readResultSet(query, resultSet);
		} catch (Throwable e) {
			LOG.error(sql, e);
		} finally {
			close(resultSet, null, callableStatement);
		}
		return list;
	}

	/**
	 * DML(Data Manipulation Language)—数据操纵语言(DELETE，UPDATE，INSERT)
	 * DQL(DataQueryLanguage )—数据查询语言(SELECT)
	 */
	public final static int executeUpdate(Connection connection, String sql, Object... params) {
		int count = -1;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = prepareStatement(connection, sql, params);
			count = preparedStatement.executeUpdate();
		} catch (Throwable e) {
			LOG.error(sql, e);
		} finally {
			close(null, preparedStatement, null);
		}
		return count;
	}

	/**
	 * Get List Map<name,value> Map as Entity
	 * 
	 * @param query
	 *            key : ResultSet Field Name , value : ResultSet Field Type
	 * @param params
	 *            PreparedStatement params
	 */
	public final static List<Map<String, Object>> queryForListMap(Connection connection, Map<String, JavaType> query,
			String sql, Object... params) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = prepareStatement(connection, sql, params);
			resultSet = preparedStatement.executeQuery();
			return readResultSet(query, resultSet);
		} catch (Throwable e) {
			LOG.error("queryForListMap " + sql, e);
		} finally {
			close(resultSet, preparedStatement, null);
		}
		return list;
	}

	/**
	 * Get List <T> by HashMap<String, CJType> query , T is Entity
	 * 
	 * @param query
	 *            key : ResultSet Field Name , value : ResultSet Field Type
	 * @param params
	 *            PreparedStatement params
	 */
	public final static <T> List<T> queryForList(Connection connection, Class<T> entityClass, Map<String, JavaType> query,
			String sql, Object... params) {
		List<T> result = new ArrayList<T>();
		List<Map<String, Object>> list = queryForListMap(connection, query, sql, params);
		try {
			T t = null;
			Set<String> set = query.keySet();
			for (Map<String, Object> map : list) {
				t = entityClass.newInstance();
				for (String key : set) {
					JavaType type = query.get(key);
					String name = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
					BZReflect.invoke(t, name, new Object[] { map.get(key) }, Class.forName(type.toString()));
				}
				result.add(t);
			}
		} catch (Throwable e) {
			LOG.error("queryForList " + sql, e);
		}
		return result;
	}

	/**
	 * Get List <T> by Class<T> , T is Entity
	 * 
	 * @param entityClass
	 *            Entity Class
	 * @param params
	 *            PreparedStatement params
	 */
	public final static <T> ArrayList<T> queryForList(Connection connection, Class<T> entityClass, String sql,
			Object... params) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<T> list = new ArrayList<T>();
		try {
			preparedStatement = prepareStatement(connection, sql, params);
			resultSet = preparedStatement.executeQuery();
			// 获取对象所有属性
			List<Field> fields = BZReflect.getAllFields(entityClass);
			T t = null;
			JavaType type = null;
			while (resultSet.next()) {
				// 根据类型信息获得对象
				t = entityClass.newInstance();
				// 填充t对象属性
				for (Field field : fields) {
					try {
						field.setAccessible(true);
						// 获取属性类型并转换成类型枚举
						type = JavaType.valueOf(field.getType().getSimpleName().toUpperCase());
						String name = field.getName();
						switch (type) {
						case INT:
						case INTEGER:
							field.setInt(t, resultSet.getInt(name));
							break;
						case FLOAT:
							field.setFloat(t, resultSet.getFloat(name));
							break;
						case DOUBLE:
							field.setDouble(t, resultSet.getDouble(name));
							break;
						case DATE:
							field.set(t, resultSet.getTimestamp(name));
							break;
						default:
							field.set(t, resultSet.getString(name));
						}
					} catch (Exception e) {
						continue;
					}
				}
				list.add(t);
			}
		} catch (Throwable e) {
			LOG.error("queryForList " + sql, e);
		} finally {
			close(resultSet, preparedStatement, null);
		}
		return list;
	}

	/**
	 * Close ResultSet, PreparedStatement, CallableStatement
	 */
	public final static void close(ResultSet resultSet, PreparedStatement preparedStatement,
			CallableStatement callableStatement) {
		try {
			if (null != resultSet) {
				resultSet.close();
				resultSet = null;
			}
			if (null != preparedStatement) {
				preparedStatement.close();
				preparedStatement = null;
			}
			if (null != callableStatement) {
				callableStatement.close();
				callableStatement = null;
			}
		} catch (Throwable e) {
			LOG.error("close resultSet preparedStatement callableStatement", e);
		}
	}

	private final static PreparedStatement prepareStatement(Connection connection, String sql, Object... params)
			throws Throwable {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			preparedStatement.setObject(i + 1, params[i]);
		}
		LOG.info(sql + "\t" + JSONObject.toJSONString(params));
		return preparedStatement;
	}

	private final static CallableStatement callableStatement(Connection connection, String sql, Object... params)
			throws Throwable {
		CallableStatement callableStatement = connection.prepareCall(sql);
		for (int i = 0; i < params.length; i++) {
			callableStatement.setObject(i + 1, params[i]);
		}
		LOG.info(sql + "\t" + JSONObject.toJSONString(params));
		return callableStatement;
	}

	private final static List<Map<String, Object>> readResultSet(Map<String, JavaType> query, ResultSet resultSet)
			throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Set<String> set = query.keySet();
		Object value = null;
		Map<String, Object> map = null;
		while (resultSet.next()) {
			map = new HashMap<String, Object>();
			for (String key : set) {
				switch (query.get(key)) {
				case INT:
				case INTEGER:
					value = resultSet.getInt(key);
					break;
				case FLOAT:
					value = resultSet.getFloat(key);
					break;
				case DATE:
					value = resultSet.getTimestamp(key);
					break;
				case STRING:
					value = resultSet.getString(key);
					break;
				default:
					value = resultSet.getObject(key);
					break;
				}
				map.put(key, value);
			}
			list.add(map);
		}
		return list;
	}
}