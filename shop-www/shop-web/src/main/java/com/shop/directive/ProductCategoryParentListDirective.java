package com.shop.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.ProductCategory;
import com.shop.service.ProductCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ProductCategoryParentListDirective extends BaseDirective {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)
	throws TemplateException, IOException {
		
		// 获取参数
		Integer productCategoryId = (Integer) getParameter("productCategoryId",params );
		// 查询根节点的商品类别
		List<ProductCategory> productCategories = productCategoryService.findParentList(productCategoryId);
		// 把数据进行输出
		setVariable(env, body, "productCategories", productCategories);
	}

}
