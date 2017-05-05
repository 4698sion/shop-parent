package com.shop.core.model;

import com.shop.core.base.BaseModel;

@SuppressWarnings("serial")
public class Attribute extends BaseModel {
	
	private Integer orders;
	private String name;
	private String options;
	private Integer propertyIndex;
	private Integer product_category;
	private String[] optionsList;
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Integer getPropertyIndex() {
		return propertyIndex;
	}
	public void setPropertyIndex(Integer propertyIndex) {
		this.propertyIndex = propertyIndex;
	}
	public Integer getProduct_category() {
		return product_category;
	}
	public void setProduct_category(Integer product_category) {
		this.product_category = product_category;
	}
	public String[] getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(String[] optionsList) {
		this.optionsList = optionsList;
	}
}
