package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.Brand;
import com.shop.dao.BrandDao;

@Service
public class BrandService {

	@Autowired
	BrandDao brandDao;
	
	public List<Brand> findBrands(Integer productCategoryId, int count ) {
		if(productCategoryId == null || count<1){
			return Collections.emptyList();
		}
		List<Brand> brands = brandDao.findBrandList(productCategoryId, count);
		return brands;
	}
}
