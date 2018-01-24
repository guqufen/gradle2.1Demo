package net.fnsco.core.redis;

import redis.clients.jedis.Jedis;

public class JedisTest {
	public static void main(String[] args) {
		try {
			String host = "r-bp1a18f92720f164.redis.rds.aliyuncs.com";// 控制台显示访问地址
			int port = 6379;
			Jedis jedis = new Jedis(host, port);
			// 鉴权信息
			jedis.auth("FnsRedis1124");// password
			String key = "redis";
			String value = "aliyun-redis";
			// select db默认为0
			jedis.select(1);
			// set一个key
			jedis.set(key, value);
			System.out.println("Set Key " + key + " Value: " + value);
			// get 设置进去的key
			String getvalue = jedis.get(key);
			System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
			jedis.quit();
			jedis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
