package com.hug.dao.bean;

import java.io.Serializable;

/**
 * @author Kael
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = -4875460209653307873L;

	static {
		System.out.println("user : static{}");
	}

	private int id;
	private String name;
	private String nickName;
	private int age;

	public User() {
	}

	public User(String name, String nickName, int age) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.age = age;
	}

	public int getId() {
		return id + 100;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
