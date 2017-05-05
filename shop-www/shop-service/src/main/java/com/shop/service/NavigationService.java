package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.Navigation;
import com.shop.core.util.AssertUtil;
import com.shop.dao.NavigationDao;

@Service
public class NavigationService {
	
	@Autowired
	private NavigationDao navigationDao;
	
	public List<Navigation> findByPosition(Integer position){

		AssertUtil.isTrue(position == null, "请选择一个位置进行查询");
		
		List<Navigation> navigations = navigationDao.findByPosition(position);
		
		return navigations;
	}
	
}
