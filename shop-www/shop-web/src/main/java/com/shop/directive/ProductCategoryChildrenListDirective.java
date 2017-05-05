package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.ProductCategory;
import com.shop.service.ProductCateogryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@SuppressWarnings("rawtypes")
@Component
public class ProductCategoryChildrenListDirective extends BaseDeirective {
	
	@Autowired
	private ProductCateogryService productCateogryService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)
	throws TemplateException, IOException {
		Integer parentId = (Integer) getParameter("productCategoryId", params);
		BigDecimal count = (BigDecimal) getParameter("count", params);
		List<ProductCategory> productCategories = productCateogryService.findChildrenList(parentId, count.intValue());
		setVariable(env, body, "productChildrenCategories", productCategories);
	}
	
}
