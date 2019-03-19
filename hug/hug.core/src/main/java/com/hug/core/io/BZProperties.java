package com.hug.core.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * @author Kael
 */
public final class BZProperties {
	private final static Log LOG = LogFactory.getLog(BZProperties.class);

	private BZProperties() {
	}

	/**
	 * 获取配置读取实列
	 * 
	 * @param path
	 *            文件的路径
	 * @return
	 * @throws Exception
	 */
	public static Properties read(String path) throws Exception {
		// InputStream inputStream =
		// CarzoneProperties.class.getClassLoader().getResourceAsStream(path);
		InputStream inputStream = new BufferedInputStream(
				new FileInputStream(BZProperties.class.getClassLoader().getResource("").getPath() + path));
		Properties p = new Properties();
		p.load(inputStream);
		inputStream.close();
		inputStream = null;
		return p;
	}

	public static Properties getProperties(String path) {
		final Properties properties = new Properties();
		FileInputStream file = null;
		try {
			file = new FileInputStream(path);
			properties.load(file);
		} catch (Exception e) {
			LOG.error("Properties Load " + path, e);
		} finally {
			try {
				file.close();
				file = null;
			} catch (IOException e) {
				LOG.error("Properties IO Close " + path, e);
			}
		}
		return properties;
	}

	public static Map<String, String> getMap(String path) {
		final Properties properties = getProperties(path);
		final Enumeration<Object> enumeration = properties.keys();
		final Map<String, String> map = new Hashtable<String, String>();
		while (enumeration.hasMoreElements()) {
			final String name = enumeration.nextElement().toString();
			map.put(name, properties.getProperty(name));
		}
		return map;
	}
}
