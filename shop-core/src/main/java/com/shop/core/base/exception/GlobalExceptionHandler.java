package com.shop.core.base.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.core.base.BaseController;
import com.shop.core.base.ResultInfo;
import com.shop.core.constant.Constant;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {
	
	@ExceptionHandler(value = ParamException.class) // 捕捉异常的注解
	@ResponseBody
	public ResultInfo hanlerParamException(ParamException paramException) {
		String message = paramException.getMessage(); 
		if (StringUtils.isBlank(message)) {
			message = Constant.ERROR_MSG;
		}
		return common(paramException.getErrorCode(), message, message);
	}
}
