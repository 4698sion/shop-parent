package com.shop.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cache.service.HotSearchKeyService;
import com.shop.core.base.BaseController;
import com.shop.core.base.ResultInfo;

@RestController
@RequestMapping("hot_search")
public class HotSearchKeyController extends BaseController{
	
	@Autowired
	private HotSearchKeyService hotSearchKeyService;
	
	@GetMapping("set")
	public ResultInfo setValue(String value){
		Long amount = hotSearchKeyService.setValue(value);
		return success(amount);
	}
	
	@GetMapping("get")
	public ResultInfo getValue(Integer limit){
		List<String> results= hotSearchKeyService.getValue(limit);
		return success(results);
	}
}
