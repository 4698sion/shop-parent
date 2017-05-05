package com.shop.core.exception;

import com.shop.core.constant.Constant;

@SuppressWarnings("serial")
public class ParamException extends RuntimeException {
	
	private int errorCode = 0;

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public ParamException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public ParamException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ParamException(String message) {
		super(message);
		this.errorCode = Constant.ERROR_CODE;
	}
	
}
