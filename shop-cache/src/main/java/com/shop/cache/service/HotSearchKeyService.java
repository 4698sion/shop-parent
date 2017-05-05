package com.shop.cache.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.util.NullableWrapper;
import org.springframework.stereotype.Service;

import com.shop.cache.config.RedisClient;
import com.shop.cache.constant.RedisKeyConstant;
import com.shop.core.exception.ParamException;

@Service
public class HotSearchKeyService {
	
	@Autowired
	private RedisClient redisClient;
	
	public Long setValue(String value) {
		
		//参数校验
		if(StringUtils.isBlank(value)){
			throw new ParamException("请输入value值");
		}
		String[] values=value.split(",");
		Long amount = redisClient.setList(RedisKeyConstant.HOT_SEARCH_KEY, values);
		return amount;
	}
	
	public List<String> getValue(Integer limit) {
		if(limit ==null){
			limit = -1;
		}
		List<String> results =redisClient.getList(RedisKeyConstant.HOT_SEARCH_KEY, limit);
		return results;
	}
}
