package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.shop.core.model.Navigation;
import com.shop.service.NavigationService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
@SuppressWarnings("rawtypes")
public class NavigationDirective implements TemplateDirectiveModel{
	
	@Autowired
	private NavigationService navigationService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		BeansWrapper beansWrapper=
				new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
		TemplateModel positionModel = (TemplateModel) params.get("position");
		BigDecimal position = (BigDecimal) beansWrapper.unwrap(positionModel);
		if(position == null){
			position = new BigDecimal(0);
		}
		
		List<Navigation> navigations=navigationService.findByPosition(position.intValue());
		
		//输出
		TemplateModel templateModel=beansWrapper.wrap(navigations);
		env.setVariable("navigations", templateModel);
		if (body != null) {
			body.render(env.getOut());
		} else {
			env.getOut().write(JSON.toJSONString(navigations));
		}
	}
}
