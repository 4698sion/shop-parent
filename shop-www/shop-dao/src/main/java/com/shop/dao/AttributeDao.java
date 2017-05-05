package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shop.core.model.Attribute;

public interface AttributeDao {
	
	@Select("SELECT id, t.`name`, t.`options` FROM xx_attribute t "
			+ "where t.product_category = #{productCategoryId} ORDER BY orders, property_index")
	List<Attribute> findProductCategoryAttrs(@Param(value = "productCategoryId") Integer productCategoryId);
	
}
