package com.shop.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.ProductCategory;
import com.shop.dao.ProductCateogryDao;

@Service
public class ProductCateogryService {
	
	@Autowired
	private ProductCateogryDao productCateogryDao;

	/**
	 * 获取第一级分类
	 * @return
	 */
	public List<ProductCategory> findRootCategories(int count) {
		if (count == 0) {
			count = 6;
		}
		List<ProductCategory> productCategories = productCateogryDao.findRootCategories(count);
		return productCategories;
	}

	public List<ProductCategory> findChildrenList(Integer parentId, int count) {
		// 参数判断
		if (parentId == null) {
			return Collections.emptyList();
		}
		List<ProductCategory> productCategories = productCateogryDao.findChildrenCategories(parentId, count);
		return productCategories;
	}

}
