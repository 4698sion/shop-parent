package com.shop.core.base;

import java.io.Serializable;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@SuppressWarnings("serial")
public class BaseDto implements Serializable {
	
	private Integer pageSize; 
	private Integer page; 
	private String sort;
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public PageBounds toPageBounds() {
		if (this.pageSize == null) {
			this.pageSize = 10;
		}
		if (this.page == null) {
			this.page = 1;
		}
		PageBounds pageBounds = new PageBounds(page, pageSize);
		if (sort != null && sort.trim().length() > 0 && sort.contains(".")) {
			pageBounds.setOrders(Order.formString(sort));
		}
		return pageBounds;
	}
}
