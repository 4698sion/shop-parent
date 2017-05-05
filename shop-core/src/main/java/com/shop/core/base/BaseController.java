package com.shop.core.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shop.core.constant.Constant;

public class BaseController {
	
	@ModelAttribute
	protected void preMethod(Model model, HttpServletRequest request) {
		model.addAttribute("ctx", request.getContextPath());
	}
	
	protected ResultInfo success(Object result) {
		return common(Constant.SUCCESS_CODE, result, Constant.SUCCESS_MSG);
	}
	
	protected ResultInfo success(Object result, String message) {
		return common(Constant.SUCCESS_CODE, result, message);
	}
	
	protected ResultInfo failure(Object result) {
		return common(Constant.ERROR_CODE, result, Constant.ERROR_MSG);
	}
	
	protected ResultInfo failure(Object result, String message) {
		return common(Constant.ERROR_CODE, result, message);
	}
	
	protected ResultInfo common(int resultCode, Object result, 
			String message) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setResultMessage(message);
		resultInfo.setResult(result);
		resultInfo.setResultCode(resultCode);
		return resultInfo;
	}
	
	
}
