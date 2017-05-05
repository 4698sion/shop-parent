package com.shop.core.util.pay;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.shop.core.constant.PayConstant;
import com.shop.core.vo.BaseOrder;

public class ShanPayUtil {

	/**
	 * 生成sign签名
	 * @param baseOrder
	 * @return
	 */
	public static String generateSign(BaseOrder baseOrder) {
		
		// 生成map
		Map<String,Object> parameter = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(baseOrder.getDesc())) {
			parameter.put("body", baseOrder.getDesc());
		}
		
		parameter.put("notify_url", PayConstant.NOTIFY_URL);
		parameter.put("out_order_no", baseOrder.getOrderNo());
		parameter.put("partner", PayConstant.PARTNER);
		parameter.put("return_url", PayConstant.RETURN_URL);
		parameter.put("subject", baseOrder.getGoodsName());
		parameter.put("total_fee", baseOrder.getAmount());
		parameter.put("user_seller", PayConstant.USER_SELLER);
		
		String preStr = createLinkstringShan(parameter);
		
		Md5Util md5Util = new Md5Util();
		return md5Util.encode(preStr + PayConstant.KEY, null);
	}
	
	/**
	 * 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param para
	 *            需要拼接的数组 return 拼接完成以后的字符串
	 * @throws PayException
	 */
	private static String createLinkstringShan(Map<String, Object> para){
		StringBuffer arg = new StringBuffer();
		if(!"".equals(para.get("body"))||para.get("body")!=null){
			arg.append("body="+para.get("body").toString()+"&");
		}
		arg.append("notify_url="+para.get("notify_url").toString()+"&");
		arg.append("out_order_no="+para.get("out_order_no").toString()+"&");
		arg.append("partner="+para.get("partner").toString()+"&");
		arg.append("return_url="+para.get("return_url").toString()+"&");
		arg.append("subject="+para.get("subject").toString()+"&");
		arg.append("total_fee="+para.get("total_fee").toString()+"&");
		arg.append("user_seller="+para.get("user_seller").toString());
		// 如果存在转义字符，那么去掉转义
		return StringEscapeUtils.unescapeJava(arg.toString());
	}
}
