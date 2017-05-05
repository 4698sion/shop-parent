package com.shop.directive;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public abstract class BaseDeirective implements TemplateDirectiveModel{
	
	private static Logger logger = LoggerFactory.getLogger(BaseDeirective.class);

	@SuppressWarnings("rawtypes")
	protected Object getParameter(String paramName,Map params) {
		BeansWrapper beansWrapper=
				new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
		TemplateModel positionMode=(TemplateModel) params.get(paramName);
		try {
			Object position = beansWrapper.unwrap(positionMode);
			return position;
		} catch (TemplateModelException e) {
			logger.error("获取参数异常：{}",e);
		}
		return null;
	}
	
	/**
	 * 把数据进行输出
	 * @param env
	 * @param body
	 * @param name
	 * @param value
	 * @throws TemplateException
	 * @throws IOException
	 */
	protected void setVariable(Environment env, TemplateDirectiveBody body, 
			String name, Object value) 
	throws TemplateException, IOException {
		
		BeansWrapper beansWrapper =new BeansWrapperBuilder(Configuration.VERSION_2_3_21).build();
	
		TemplateModel templateModel = beansWrapper.wrap(value);
		
		env.setVariable(name, templateModel);
		
		if (body != null) {
			body.render(env.getOut());
		}
	}
}
