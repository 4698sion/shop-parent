package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shop.core.model.ProductCategory;

public interface ProductCategoryDao {
	@Select("SELECT id,t.`name` FROM xx_product_category t "
			+ "WHERE parent is null ORDER BY orders LIMIT #{count}")
	List<ProductCategory> findRootList(@Param(value="count")int count);
	
	@Select("SELECT id,t.`name` FROM xx_product_category t "
			+ "WHERE t.parent=#{parentId} ORDER BY orders LIMIT #{count}")
	List<ProductCategory> findChildrenList(@Param(value="parentId")Integer productCategoryId,@Param(value="count")int count);
	
	@Select("SELECT id, t.`name`, tree_path,@ grade FROM xx_product_category t where id = #{productCategoryId}")
	ProductCategory findById(@Param(value="productCategoryId")Integer productCategoryId);
	
	@Select("SELECT id, t.`name`, tree_path, grade FROM xx_product_category t where id in (${ids})")
	List<ProductCategory> findParentCategories(@Param(value="ids")String ids);
	
}
