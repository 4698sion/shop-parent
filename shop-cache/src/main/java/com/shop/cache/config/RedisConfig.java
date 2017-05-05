package com.shop.cache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@SuppressWarnings("resource")
public class RedisConfig {
	
	@Value("${jedis.pool.config.maxTotal}") // 引用application.properties里面的值
	private int maxTatal;
	@Value("${jedis.pool.config.maxIdle}")
	private int maxIdle;
	@Value("${jedis.pool.config.maxWaitMillis}")
	private int maxWaitMillis;
	@Value("${jedis.pool.config.timeout}")
	private int timeout;
	
	@Value("${jedis.pool.master.host}")
	private String masterHost;
	@Value("${jedis.pool.master.port}")
	private int masterPort;
	@Value("${jedis.pool.master.auth}")
	private String masterAuth;
	
	@Value("${jedis.pool.slave.host}")
	private String slaveHost;
	@Value("${jedis.pool.slave.port}")
	private int slavePort;
	@Value("${jedis.pool.slave.auth}")
	private String slaveAuth;
	
	@Bean(name = "master.jedis")
	public Jedis masterJedis() {
		JedisPoolConfig config = jedisPoolConfig();
		JedisPool jedisPool = new JedisPool(config, masterHost, 
				masterPort, timeout, masterAuth);
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
	
	@Bean(name = "slave.jedis")
	public Jedis slaveJedis() {
		JedisPoolConfig config = jedisPoolConfig();
		JedisPool jedisPool = new JedisPool(config, slaveHost, 
				slavePort, timeout, slaveAuth);
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
	
	
	private JedisPoolConfig jedisPoolConfig () {  
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxTotal(100);  
		config.setMaxIdle(10);  
		config.setMaxWaitMillis(10000);  
		return config;  
	} 
	
}
