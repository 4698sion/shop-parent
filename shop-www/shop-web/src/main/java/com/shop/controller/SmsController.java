package com.shop.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.core.base.BaseController;
import com.shop.core.base.ResultInfo;
import com.shop.core.base.exception.ParamException;
import com.shop.core.constant.Constant;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@RestController
@RequestMapping("sms")
public class SmsController extends BaseController{
	
	 private static Logger logger = LoggerFactory.getLogger(SmsController.class);

	    @Value("${sms.url}")
	    private String smsUrl;
	    @Value("${sms.appkey}")
	    private String appKey;
	    @Value("${sms.appsecret}")
	    private String appSecret;
	    @Value("${sms.type}")
	    private String smsType;
	    @Value("${sms.sms_free_signname}")
	    private String smsFreeSignName;
	    @Value("${sms.sms_template_code}")
	    private String smsTemplateCode;
	    
	    
	    String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23782541";
        String secret = "33f5fad8655b4c9f02ddec2159f5300b";
	    
	    @RequestMapping("send")
	    @ResponseBody
	    public ResultInfo sendVerifyCode(String phone,HttpServletRequest request){
	    	if(StringUtils.isBlank(phone)){
	    		throw new ParamException("请输入手机号码");
	    	}
	    	
	    	String verfiedCode = (String)request.getSession().getAttribute(Constant.VERIFYCODE_SESSION_KEY);
	    	if(StringUtils.isNotBlank(verfiedCode)){
	    		return success("发送成功");
	    	}
	    	

	    	
	    	TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
	        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
	        req.setExtend( "" );
	        req.setSmsType("normal");
	        req.setSmsFreeSignName("郁文景");
	        int verifyCode=(int)((Math.random()*9+1)*100000);
	        
	        req.setSmsParamString( "{number:"+"'"+verifyCode+"'"+"}" );	        
	        req.setRecNum(phone);
	        req.setSmsTemplateCode("SMS_63970776");
	        AlibabaAliqinFcSmsNumSendResponse rsp;
	        
			try {
				rsp = client.execute(req);
				logger.info(rsp.getBody());
				request.getSession().setAttribute(Constant.VERIFYCODE_SESSION_KEY, verifyCode + "");
	            request.getSession().setMaxInactiveInterval(300000); // 验证码5分钟过期 可以存放redis
			} catch (ApiException e1) {
				System.out.println("error");
			}
			return success("发送成功");
	    }
}