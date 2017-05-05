package com.shop.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.Attribute;
import com.shop.service.AttributeService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AttributeDirective extends BaseDirective {
	
	@Autowired
	private AttributeService attributeService;

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)
	throws TemplateException, IOException {
		Integer productCategoryId = (Integer) getParameter("productCategoryId", params); // 分类ID
		List<Attribute> attributes = attributeService.findProductCategoryAttrs(productCategoryId);
		if (attributes != null && attributes.size() > 0) {
			for (Attribute attribute : attributes) {
				String options = attribute.getOptions();
				if (StringUtils.isBlank(options)) {
					continue;
				}
//				["3英寸以下","3-4英寸","4-5英寸","5-6英寸","6-7英寸","7-8英寸","8英寸以上"]
				String optionSubs = options.substring(1, options.lastIndexOf("]"));
				String[] optionsArr = optionSubs.split(",");
				List<String> subStrs = new ArrayList<>();
				for(String str:optionsArr) {
					subStrs.add(str.substring(1, str.length() - 1));
				}
				attribute.setOptionsList(subStrs.toArray(new String[]{}));
			}
		}
		setVariable(env, body, "attributes", attributes);
	}
}
