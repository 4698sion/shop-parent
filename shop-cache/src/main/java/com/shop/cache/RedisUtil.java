package com.shop.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	public static void main(String[] args) {
		JedisPoolConfig config = jedisPoolConfig();
		@SuppressWarnings("resource")
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 
				6379, 100, "123456");
		Jedis jedis = jedisPool.getResource();
		
		jedis.set("hello", "world");
		System.out.println(jedis.get("hello"));
	}
	
	private static JedisPoolConfig jedisPoolConfig () {  
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxTotal(100);  
		config.setMaxIdle(10);  
		config.setMaxWaitMillis(10000);  
		return config;  
	} 
}
