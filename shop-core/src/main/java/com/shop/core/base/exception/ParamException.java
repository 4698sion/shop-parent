package com.shop.core.base.exception;

@SuppressWarnings("serial")
public class ParamException extends RuntimeException {
	
	private int errorCode = 0;
	
	public ParamException() {
		
	}
	
	public ParamException(String errorMessage) {
		super(errorMessage);
		
	}
	
	public ParamException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
