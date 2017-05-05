package com.shop.core.dto;

import java.math.BigDecimal;

import com.shop.core.base.BaseDto;

@SuppressWarnings("serial")
public class GoodsDto extends BaseDto {
	
	private String keyword;
	private BigDecimal startPrice;
	private BigDecimal endPrice;
	private Integer productCategoryId;
	private String treePath;
	private Integer brandId;
	private String attributeValue0;
	private String attributeValue1;
	private String attributeValue2;
	private String attributeValue3;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	public Integer getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Integer productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getattributeValue0() {
		return attributeValue0;
	}
	public void setattributeValue0(String attributeValue0) {
		this.attributeValue0 = attributeValue0;
	}
	public String getattributeValue1() {
		return attributeValue1;
	}
	public void setattributeValue1(String attributeValue1) {
		this.attributeValue1 = attributeValue1;
	}
	public String getattributeValue2() {
		return attributeValue2;
	}
	public void setattributeValue2(String attributeValue2) {
		this.attributeValue2 = attributeValue2;
	}
	public String getattributeValue3() {
		return attributeValue3;
	}
	public void setattributeValue3(String attributeValue3) {
		this.attributeValue3 = attributeValue3;
	}
}
