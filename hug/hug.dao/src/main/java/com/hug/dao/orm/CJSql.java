package com.hug.dao.orm;

import java.util.Map;
import java.util.Set;

import com.hug.dao.orm.CJType.JavaType;

public class CJSql {
	/**
	 * 空格
	 */
	public static final String SPANCE = " ";
	/**
	 * 单引号 ""
	 */
	public static final String ACCENT = "\"";
	/**
	 * 间隔符 ``
	 */
	public static final String QUOTES = "`";
	/**
	 * 逗号 ,
	 */
	public static final String COMMA = ",";
	/**
	 * 等于号 =
	 */
	public static final String EQUAL = "=";
	/**
	 * 问号 ?
	 */
	public static final String QUESTION = "?";
	public static final String COUNT = "count";
	public static final String LEFTBRACKET = "(";
	public static final String RIGHTRACKET = ")";
	public static final String LESS = spance("<");
	public static final String MORE = spance(">");
	public static final String SELECT = spance("select");
	public static final String UPDATE = spance("update");
	public static final String DELETE = spance("delete");
	public static final String INSERT = spance("insert");
	public static final String FROM = spance("from");
	public static final String SET = spance("set");
	public static final String INTO = spance("into");
	public static final String VALUES = spance("values");
	public static final String WHERE = spance("where");
	public static final String AND = spance("and");
	public static final String NULL = spance("null");
	public static final String ISNULL = spance("is") + NULL;
	public static final String ORDERBY = spance("order by");
	public static final String DESC = spance("desc");
	public static final String LIMIT = spance("limit");

	/**
	 * @return xxx
	 */
	public static final String spance(String param) {
		return SPANCE + param + SPANCE;
	}

	/**
	 * @return (xxx)
	 */
	public static final String bracket(String param) {
		return LEFTBRACKET + param + RIGHTRACKET;
	}

	/**
	 * @return `xxx`
	 */
	public static final String key(String param) {
		return QUOTES + param + QUOTES;
	}

	public static final String select(String tableName) {
		return SELECT + "t.*" + FROM + tableName + " t";
	}

	public static final String selectCount(String tableName) {
		return SELECT + COUNT + bracket("1") + spance(COUNT) + FROM + tableName;
	}

	/**
	 * @return select x,xx,xxx,xxxx from
	 */
	public static final String selectFrom(Map<String, JavaType> query) {
		StringBuilder stringBuilder = new StringBuilder(SELECT);
		Set<String> set = query.keySet();
		for (String key : set)
			stringBuilder.append(key + COMMA);
		String sql = stringBuilder.toString();
		return sql.substring(0, sql.length() - 1) + FROM;
	}

	/**
	 * @param params
	 *            insert into (x,xx,xxx,xxxx)
	 * @param values
	 *            values (?,?,?,?)
	 */
	public static final String insert(String tableName, Object[] name, Object... values) {
		StringBuilder field = new StringBuilder(INSERT + INTO + key(tableName) + LEFTBRACKET + key(name[0].toString()));
		StringBuilder value = new StringBuilder(RIGHTRACKET + VALUES + LEFTBRACKET + QUESTION);
		for (int i = 1; i < name.length; i++) {
			field.append(COMMA + key(name[i].toString()));
			value.append(COMMA + QUESTION);
		}
		return field.append(value) + RIGHTRACKET;
	}
	
	/**
	 * delete from tableName
	 */
	public static final String delete(String tableName) {
		return DELETE + FROM + tableName + SPANCE;
	}

	/**
	 * update tableName set
	 */
	public static final String update(String tableName) {
		return UPDATE + tableName + SET;
	}

	/**
	 * update tableName set x,x,x, where
	 */
	public static final String update(String tableName, String[] sets, String where, Object... params) {
		StringBuilder stringBuilder = new StringBuilder(update(tableName));
		for (String set : sets) {
			stringBuilder.append(set + EQUAL + QUESTION + COMMA);
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1) + where;
	}
}
