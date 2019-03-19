package com.hug.dao.orm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.hug.core.io.BZProperties;

/**
 * @author Kael
 */
public final class CJDBPool {
	private static final Log LOG = LogFactory.getLog(CJDBPool.class);
	private static final Map<CJDB, DataSource> dataSourceMap = new Hashtable<CJDB, DataSource>();
	private static final String JNDI = "java:comp/env/";
	private static final String PROPERTIES = ".properties";
	/**
	 * 测试库后缀+dev
	 */
	private static final String DEV = "";
	private static Context context;

	private CJDBPool() {
	}

	static {
		CJDB[] db = CJDB.values();
		for (CJDB cjdb : db) {
			getDataSourceByProperties(cjdb);
			// getDataSourceByJNDI(cjdb);
		}
	}

	public final static Connection getConnection(CJDB cjdb) {
		try {
			return dataSourceMap.get(cjdb).getConnection();
		} catch (SQLException e) {
			LOG.error("Get Connection " + cjdb.toString(), e);
		}
		return null;
	}

	private final static void getDataSourceByProperties(CJDB cjdb) {
		try {
			dataSourceMap.put(cjdb, DruidDataSourceFactory
					.createDataSource(BZProperties.getProperties(CJDBPool.class
							.getClassLoader().getResource("").getPath()
							+ cjdb.toString() + DEV + PROPERTIES)));
		} catch (Exception e) {
			LOG.error("Init DataSource By Properties " + cjdb.toString(), e);
		}
	}
	@SuppressWarnings("unused")
	private final static void getDataSourceByJNDI(CJDB cjdb) {
		try {
			// 懒汉模式
			if (null == context) {// 第一次校验为了防止后续的阻塞
				synchronized (CJDBPool.class) {
					if (null == context)// 第二次校验是为了校验时候需要实例化
						context = new InitialContext();
				}
			}
			dataSourceMap.put(cjdb,
					(DataSource) context.lookup(JNDI + cjdb.toString() + DEV));
		} catch (Exception e) {
			LOG.error("Init DataSource By JNDI " + cjdb.toString(), e);
		}
	}

	public static void main(String[] args) throws SQLException {
	}
}
