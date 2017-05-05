package com.shop.core.dto;

import com.shop.core.base.BaseDto;

@SuppressWarnings("serial")
public class UserDto extends BaseDto {
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
}
