package com.shop.core.model;

import java.beans.Transient;

import org.apache.commons.lang3.StringUtils;

import com.shop.core.base.BaseModel;


@SuppressWarnings("serial")
public class ProductCategory extends BaseModel {
	
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 路径前缀 */
	private static final String PATH_PREFIX = "/goods/list";
	
	private int orders;
	/** 层级 */
	private int grade;
	/** 名称 */
	private String name;
	/** 页面描述*/
	private String seoDescription;
	/** 页面关键词 */
	private String seoKeywords;
	/** 页面标题 */
	private String seoTitle;
	/** 树路径 */
	private String treePath;
	/**父级节点 */
	private int parent;
	
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeoDescription() {
		return seoDescription;
	}
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	public String getSeoKeywords() {
		return seoKeywords;
	}
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	public String getSeoTitle() {
		return seoTitle;
	}
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	/**
	 * 获取所有上级分类ID
	 * 
	 * @return 所有上级分类ID
	 */
	@Transient
	public Integer[] getParentIds() {
		String[] parentIds = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		Integer[] result = new Integer[parentIds.length];
		for (int i = 0; i < parentIds.length; i++) {
			result[i] = Integer.valueOf(parentIds[i]);
		}
		return result;
	}
	
	/**
	 * 获取路径
	 * 
	 * @return 路径
	 */
	@Transient
	public String getPath() {
		return getId() != null ? PATH_PREFIX + "/" + getId() : null;
	}
	
}
