package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.Attribute;
import com.shop.dao.AttributeDao;

@Service
public class AttributeService {
	
	@Autowired
	public AttributeDao attributeDao;

	public List<Attribute> findProductCategoryAttrs(Integer productCategoryId) {
		List<Attribute> attributes = attributeDao.findProductCategoryAttrs(productCategoryId);
		return attributes;
	}

}
