package com.shop.core.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseOrder implements Serializable {
	
	private String orderNo;
	private String amount;
	private String goodsName;
	private String desc;
	public String getOrderNo() {
		return orderNo;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public String getDesc() {
		return desc;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
