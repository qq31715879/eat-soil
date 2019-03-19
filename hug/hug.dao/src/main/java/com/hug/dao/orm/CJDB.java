package com.hug.dao.orm;

/**
 * @author Kael
 */
public enum CJDB {
	MEIHU("meihu");

	private String db;

	private CJDB(String db) {
		this.db = db;
	}

	@Override
	public String toString() {
		return db;
	}
}
