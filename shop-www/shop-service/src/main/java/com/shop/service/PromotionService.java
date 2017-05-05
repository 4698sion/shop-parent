package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.Promotion;
import com.shop.dao.PromotionDao;

@Service
public class PromotionService {
	
	@Autowired
	private PromotionDao promotionDao;

	/**
	 * 获取优惠促销
	 * @param productCategoryId
	 * @param count
	 * @param hasEnded
	 * @return
	 */
	public List<Promotion> findPromotions(Integer productCategoryId, 
			int count, Boolean hasEnded) {
		
		List<Promotion> promotions = promotionDao.findPromotions(productCategoryId, hasEnded, count);
		
		return promotions;
	}
	
}
