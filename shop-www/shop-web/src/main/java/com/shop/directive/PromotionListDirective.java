package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.Promotion;
import com.shop.service.PromotionService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@SuppressWarnings("rawtypes")
public class PromotionListDirective extends BaseDeirective {

	@Autowired
	private PromotionService promotionService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Integer productCategoryId = (Integer) getParameter("productCategoryId", params);
		BigDecimal count = (BigDecimal) getParameter("count", params);
		Boolean hasEnd=(Boolean) getParameter("hasEnd", params);
		List<Promotion> promotions=promotionService.findPromotions(productCategoryId, count.intValue(), hasEnd);
		
		setVariable(env, body, "promotions", promotions);
	}

}
