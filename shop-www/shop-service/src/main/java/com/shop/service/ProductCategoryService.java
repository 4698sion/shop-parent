package com.shop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.constant.ProductCategoryGrade;
import com.shop.core.exception.ParamException;
import com.shop.core.model.ProductCategory;
import com.shop.core.util.AssertUtil;
import com.shop.dao.ProductCategoryDao;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	
	/**
	 * 获取跟级商品类别
	 * @param count
	 * @return
	 */
	public List<ProductCategory> findRootList(int count) {
		List<ProductCategory> productCategories = productCategoryDao.findRootList(count);
		return productCategories;
	}

	/**
	 * 获取子类名称
	 * @param productCategoryId
	 * @param count
	 * @param recursive
	 * @return
	 */
	public List<ProductCategory> findChildrenList(Integer productCategoryId, 
			int count, Boolean recursive) {
		if (productCategoryId == null) {
			throw new ParamException("请输入类别ID");
		}
		if (recursive == null) {
			throw new ParamException("请选择查询的类型");
		}
		List<ProductCategory> productCategories = null;
		if (!recursive) { // 只查询出子类
			productCategories = productCategoryDao.findChildrenList(productCategoryId, count);
		} else {
			
		}
		return productCategories;
	}
	
	/**
	 * 根据ID获取分类信息
	 * @param productCategoryId
	 * @return
	 */
	public ProductCategory findById(Integer productCategoryId) {
		
		AssertUtil.isTrue(productCategoryId == null || productCategoryId < 1, "请选择一个分类");
		
		ProductCategory productCategory = productCategoryDao.findById(productCategoryId);
		
		AssertUtil.isTrue(productCategory == null, "该分类不存在");
		
		return productCategory;
	}
	
	/**
	 * 获取父级信息
	 * @param productCategoryId8
	 * @return
	 */
	public List<ProductCategory> findParentList(Integer productCategoryId) {
		ProductCategory productCategory = findById(productCategoryId);
		if (productCategory.getGrade() == ProductCategoryGrade.ROOT.getGrade()) { // 如果是根级就不需要查询父级
			return Collections.emptyList();
		}
		// 获取parentIds
		String treePath = productCategory.getTreePath();
		treePath = treePath.substring(treePath.indexOf(",") + 1, treePath.lastIndexOf(","));
		List<ProductCategory> productCategories = new ArrayList<>();
		if (productCategory.getGrade() == ProductCategoryGrade.SECOND.getGrade()) { // 如果是第二级的话就单个查询
			ProductCategory parentProductCategory = findById(Integer.parseInt(treePath));
			productCategories.add(parentProductCategory);
		} else {
			productCategories = productCategoryDao.findParentCategories(treePath);
		}
		return productCategories;
	}

}
