package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shop.core.model.Article;

public interface ArticleDao {
	
	@Select("SELECT id,title FROM xx_article t WHERE article_category=#{articleCategoryId}"
			+ " ORDER BY t.hits desc LIMIT #{count}")
	List<Article> findArticleList(@Param(value="articleCategoryId")Integer articleCategoryId,
			@Param(value="count")int count);
	
}
