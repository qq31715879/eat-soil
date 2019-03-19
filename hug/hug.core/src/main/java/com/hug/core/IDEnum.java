package com.hug.core;

public enum IDEnum {
	DRIVERTITLE("drivertitle"), DRIVERBODY("driverbody");
	private String id;

	private IDEnum(String id) {
		this.id = id;
	}

	public String toString() {
		return id;
	}
}
