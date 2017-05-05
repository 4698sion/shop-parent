package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.AdPosition;
import com.shop.dao.AdPositionDao;

@Service
public class AdpositonService {
	@Autowired
	private AdPositionDao adPositionDao;
	
	public AdPosition findById(int id) {
		
		AdPosition adPosition=adPositionDao.findById(id);
		return adPosition;
	}
}
