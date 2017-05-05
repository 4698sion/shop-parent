package com.shop.core.constant;

public enum GoodsOrderType {
	
	isTop("is_top.desc", "置顶降序"), 
	priceAsc("price.asc", "价格升序 "), 
	priceDesc("price.desc", "价格降序"), 
	salesDesc("sales.desc", "销量降序"), 
	scoreDesc("score.desc", "评分降序"), 
	createDateDesc("create_date.desc", "日期降序");
	
	private String sort; // xxx.asc
	private String showOrderType; // 置顶降序 价格升序 价格降序 销量降序 评分降序 日期降序
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getShowOrderType() {
		return showOrderType;
	}
	public void setShowOrderType(String showOrderType) {
		this.showOrderType = showOrderType;
	}
	private GoodsOrderType() {
		
	}
	private GoodsOrderType(String sort, String showOrderType) {
		this.sort = sort;
		this.showOrderType = showOrderType;
	}
	
	public static GoodsOrderType findBySort(String sort) {
		for (GoodsOrderType orderType : GoodsOrderType.values()) {
			if (orderType.getSort().equals(sort)) {
				return orderType;
			}
		}
		return null;
	}
	
}
