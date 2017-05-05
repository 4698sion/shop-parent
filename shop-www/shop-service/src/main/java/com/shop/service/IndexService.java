package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shop.core.base.ResultInfo;

@Service
public class IndexService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	public List<String> findHotSearchKeys() {
		ResultInfo resultInfo = restTemplate.getForObject("http://localhost:8082/hot_search/get", 
				ResultInfo.class);
		return (List<String>) resultInfo.getResult();
	}

}
