package com.shop.core.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpecificationValue implements Serializable {

	private Integer id;
	private String value;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
