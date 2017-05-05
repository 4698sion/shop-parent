package com.shop.cache.config;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class RedisClient {
	
//	@Autowired
//	@Qualifier(value="master.jedis")
//	private Jedis masterJedis;
//	
//	@Autowired
//	@Qualifier(value="slave.jedis")
//	private Jedis slaveJedis;
	
	@Resource(name="master.jedis")
	private Jedis masterJedis;
	
	@Resource(name="slave.jedis")
	private Jedis slaveJedis;
	
	public void set(String key, String value) {
		if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
			masterJedis.set(key, value);
		}
	}
	
	public String get(String key) {
		if (StringUtils.isBlank(key)) {
			return "";
		}
		return slaveJedis.get(key);
	}
	
	
	public Long setList(String key, String[] values) {
		if (StringUtils.isBlank(key) || values == null || values.length < 1) {
			return 0L;
		}
		Long amount = masterJedis.rpush(key, values);
		return amount;
	}
	
	public List<String> getList(String key, int limit) {
		if (StringUtils.isBlank(key)) {
			return Collections.emptyList();
		}
		List<String> result = slaveJedis.lrange(key, 0, limit);
		return result;
	}
	
}
