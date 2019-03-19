package com.hug.core.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description 序列化和反序列化
 * @author chenjian
 * @date 2016年9月22日 下午4:32:19
 */
public class BZSerialize {

	/**
	 * 序列化
	 */
	public static <T> byte[] serialize(T t) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(t);
		byte[] bytes = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();
		objectOutputStream.close();
		byteArrayOutputStream = null;
		objectOutputStream = null;
		return bytes;
	}

	/**
	 * 反序列化
	 */
	public static <T> T unserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		@SuppressWarnings("unchecked")
		T t = (T) objectInputStream.readObject();
		byteArrayInputStream.close();
		objectInputStream.close();
		byteArrayInputStream = null;
		objectInputStream = null;
		return t;
	}

}
