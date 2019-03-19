package com.hugl.core.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hug.core.io.BZProperties;
import com.hug.core.io.BZSerialize;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public enum BZRedis {
	INSTANCE;
	private static final String OK = "OK";
	private static final String PREFIX = "";
	private static String IP;
	private static String PWD;
	private static int MAXLDLE = 5;
	private static final int PORT = 6379;
	private static JedisPool jedisPool;// 非切片连接池
	 private static final int DB = 6;
	// private static ShardedJedisPool shardedJedisPool;// 切片连接池

	static {
		if (null == jedisPool) {
			synchronized (BZRedis.class) {
				if (null == jedisPool) {
					try {
						Properties properties = BZProperties.read("client.properties");
						IP = properties.getProperty("carzone.redis.ip");
						PWD = properties.getProperty("carzone.redis.pwd");
						MAXLDLE = Integer.valueOf(properties.getProperty("carzone.redis.maxIdle"));
						IP.toString();
					} catch (Exception e) {
						System.err.println("=-=-=-=-=-=Redis Pool Start Without Properties,IP : 127.0.0.1=-=-=-=-=-=");
						IP = "123.56.234.98";
						PWD = "hongxin@mhysa";
					}
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxIdle(MAXLDLE);
					config.setMaxWaitMillis(1000l);
					config.setTestOnBorrow(false);
					jedisPool = new JedisPool(config, IP, PORT, 6000, PWD);
					// jedisPool = new JedisPool(config, IP, PORT, 6000);
				}
			}
		}
	}

	public <T> boolean set(String key, T value, int... seconds) throws Exception {
		boolean status = false;
		try (Jedis jedis = getJedis()) {
			byte[] keys = wrapKey(key);
			byte[] values = BZSerialize.serialize(value);
			status = OK.equals(seconds.length == 0 ? jedis.set(keys, values) : jedis.setex(keys, seconds[0], values));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return status;
	}

	public <T> boolean lpush(String key, T value) throws Exception {
		boolean status = false;
		try (Jedis jedis = getJedis()) {
			status = OK.equals(jedis.lpush(wrapKey(key), BZSerialize.serialize(value)));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return status;
	}

	public <T> List<T> lrange(String key, int start, int end) throws Exception {
		List<T> list = new ArrayList<T>();
		try (Jedis jedis = getJedis()) {
			byte[] keys = wrapKey(key);
			List<byte[]> jlist = jedis.lrange(keys, start, end);
			for (byte[] bs : jlist) {
				T t = BZSerialize.unserialize(bs);
				list.add(t);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return list;
	}

	public <T> List<T> lrange(String key) throws Exception {
		return lrange(key, 0, -1);
	}

	public void lpop(String key) {
		try (Jedis jedis = getJedis()) {
			jedis.lpop(wrapKey(key));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void ldel(String key, int length) {
		try (Jedis jedis = getJedis()) {
			jedis.ltrim(wrapKey(key), length, length);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public int llen(String key) {
		int length = 0;
		try (Jedis jedis = getJedis()) {
			length = Integer.valueOf(jedis.llen(wrapKey(key)) + "");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return length;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) throws Exception {
		T t = null;
		try (Jedis jedis = getJedis()) {
			byte[] bytes = jedis.get(wrapKey(key));
			t = bytes == null ? null : (T) BZSerialize.unserialize(bytes);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return t;
	}

	public boolean exists(String key) {
		boolean exists = false;
		try (Jedis jedis = getJedis()) {
			exists = jedis.exists(wrapKey(key));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public void del(String key) {
		try (Jedis jedis = getJedis()) {
			jedis.del(wrapKey(key));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	private byte[] wrapKey(String key) {
		return (PREFIX + key).getBytes();
	}

	public JedisPool getPool() {
		return jedisPool;
	}

	public Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		jedis.select(DB);
		return jedis;
	}

	public static void main(String[] args) throws Throwable {
		BZRedis.INSTANCE.set("demo", "demo", 50);
		String demo = BZRedis.INSTANCE.get("demo");
		System.out.println(demo);

	}
}
