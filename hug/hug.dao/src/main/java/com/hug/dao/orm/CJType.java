package com.hug.dao.orm;

public class CJType {
	private String alias;
	private String db;
	private String java;

	public CJType(String alias, String db, String java) {
		super();
		this.alias = alias;
		this.db = db;
		this.java = java;
	}

	public enum JavaType {
		INT("int"), INTEGER("java.lang.Integer"), DOUBLE("java.lang.Double"), FLOAT("java.lang.Float"), DATE(
				"java.util.Date"), STRING("java.lang.String");
		private String className;

		private JavaType(final String className) {
			this.className = className;
		}

		@Override
		public String toString() {
			return className;
		}
	}

	/**
	 * get JavaType by DatabaseType
	 */
	public final static String getJavaType(String type) {
		type = type.toLowerCase();
		if (type.contains("char")) {
			return "String";
		}
		if (type.equals("bigint")) {
			return "Long";
		}
		if (type.contains("int")) {
			return "Integer";
		}
		if (type.equals("decimal")) {
			return "BigDecimal";
		}
		if (type.contains("date") || type.contains("time")) {
			return "Date";
		}
		return type;
	}

	/**
	 * public enum DBType { INT(new
	 * CJType(CJType.INT,"int","java.lang.Integer")), ;
	 * 
	 * private CJType type;
	 * 
	 * private DBType(CJType type){ this.type = type; }
	 * 
	 * public CJType getType(){ return type; } }
	 */

	public String getAlias() {
		return alias;
	}

	public String getDb() {
		return db;
	}

	public String getJava() {
		return java;
	}
}
