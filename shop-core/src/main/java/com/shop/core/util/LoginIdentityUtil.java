package com.shop.core.util;

import javax.servlet.http.HttpServletRequest;

import com.shop.core.constant.Constant;
import com.shop.core.vo.LoginIndentity;

public class LoginIdentityUtil {
	
	public static LoginIndentity getFromSession(HttpServletRequest request) {
		LoginIndentity loginIndentity = (LoginIndentity) request.getSession()
				.getAttribute(Constant.USER_SESSION_KEY);
		return loginIndentity;
	}
	
	public static Integer getFromLoginId(HttpServletRequest request) {
		LoginIndentity loginIndentity = (LoginIndentity) request.getSession()
				.getAttribute(Constant.USER_SESSION_KEY);
		if (loginIndentity != null) {
			return loginIndentity.getId();
		} 
		return null;
	}
	
	
}
