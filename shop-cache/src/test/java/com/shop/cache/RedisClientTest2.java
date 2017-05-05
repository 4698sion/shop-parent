package com.shop.cache;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.cache.config.RedisClient;

public class RedisClientTest2 extends BaseTest {
	
	@Autowired
	private RedisClient redisClient;
	
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
	
	@Test
	public void testSetList() {
		try {
			Long result = redisClient.setList("HOT_SEARCH", new String[]{"苹果","三星",
					"索尼","华为","魅族","佳能","华硕","美的","格力"});
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetList() {
		try {
			List<String> result = redisClient.getList("HOT_SEARCH_KEY", 7);
			System.out.println(Arrays.toString(result.toArray(new String[]{})));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
