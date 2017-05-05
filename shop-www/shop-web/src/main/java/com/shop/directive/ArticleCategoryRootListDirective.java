package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.ArticleCategory;
import com.shop.service.ArticleCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@SuppressWarnings("rawtypes")
public class ArticleCategoryRootListDirective extends BaseDeirective {

	@Autowired
	private ArticleCategoryService  articleCategoryService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		BigDecimal count = (BigDecimal) getParameter("count",params);
		List<ArticleCategory> articleCategories=articleCategoryService.findList(count.intValue());
		setVariable(env, body, "articleCategories", articleCategories);
	}

}
