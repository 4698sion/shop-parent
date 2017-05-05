package com.shop.core.constant;

public enum GoodsSortType {

	//	is_top.desc 置顶降序

	isTop("is_top.desc", "置顶降序"), 
	priceAsc("price.asc", "价格升序 "), 
	priceDesc("price.desc", "价格降序"), 
	salesDesc("sales.desc", "销量降序"), 
	scoreDesc("score.desc", "评分降序"), 
	createDateDesc("create_date.desc", "日期降序");
	
	private String sort;
	private String showOrderType;

	GoodsSortType(String sort, String showOrderType) {
		this.sort = sort;
		this.showOrderType = showOrderType;
	}
	
	GoodsSortType() {
	}
	
	public static String findByType(String sort) {
		GoodsSortType[] orderTypes = GoodsSortType.values();
		for (GoodsSortType goodsSortType : orderTypes) {
			if (goodsSortType.getSort().equals(sort)) {
				return goodsSortType.getShowOrderType();
			}
		}
		return null;
	}

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

}
