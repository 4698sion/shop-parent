package com.shop.directive;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.core.model.Goods;
import com.shop.service.GoodsService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class HotGoodsListDirective extends BaseDirective {
	
	@Autowired
	private GoodsService goodsService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
		// 获取参数
		BigDecimal count = (BigDecimal) getParameter("count", params);
		BigDecimal tagId = (BigDecimal) getParameter("tagId", params);
		// 查询热门数据
		List<Goods> goodsList = goodsService.findHotGoodsList(tagId.intValue(), count.intValue());
		
		// 输出数据
		setVariable(env, body, "goodsList", goodsList);
		
	}

}
