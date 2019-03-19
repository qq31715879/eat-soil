package com.hug.core.data.structure.map;

import java.util.HashMap;

public class CJHashMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1806894066339300552L;

	public CJHashMap() {
		super();
	}

	public CJHashMap(K key, V value) {
		this();
		this.put(key, value);
	}

	public CJHashMap<K, V> set(K key, V value) {
		put(key, value);
		return this;
	}
}
