package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.Article;
import com.shop.service.ArticleService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


@Component
@SuppressWarnings("rawtypes")
public class ArticleListDirective extends BaseDeirective{
	
	@Autowired
	private ArticleService articleService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		BigDecimal count = (BigDecimal) getParameter("count", params);
		Integer categoryId=(Integer) getParameter("categoryId", params);
		List<Article> articles=articleService.findArticleList(categoryId, count.intValue());
		setVariable(env, body, "articles", articles);
		
	}
	
	

}
