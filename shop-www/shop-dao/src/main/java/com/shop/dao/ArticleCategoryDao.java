package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shop.core.model.ArticleCategory;

public interface ArticleCategoryDao {
	@Select("SELECT id,`name` FROM xx_article_category ORDER BY orders LIMIT #{count}")
	List<ArticleCategory> findList(@Param(value="count")int count);
}
