package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.model.Article;
import com.shop.dao.ArticleDao;


@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public List<Article> findArticleList(Integer categoryId,int count) {
		List<Article> articles=articleDao.findArticleList(categoryId, count);
		return articles;
	}
}
