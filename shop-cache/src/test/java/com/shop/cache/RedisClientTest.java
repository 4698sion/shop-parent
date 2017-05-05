package com.shop.cache;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.shop.cache.config.RedisClient;
@SuppressWarnings("resource")
public class RedisClientTest {
	
	private RedisClient redisClient;
	
	@Before
	public void loadAc() {
		FileSystemXmlApplicationContext ac = new FileSystemXmlApplicationContext("classpath:servlet-context.xml");
		redisClient = ac.getBean(RedisClient.class);
	}
	
	@Test
	public void testSet() {
		try {
			redisClient.set("name", "老李");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGet() {
		try {
			String name = redisClient.get("name");
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
