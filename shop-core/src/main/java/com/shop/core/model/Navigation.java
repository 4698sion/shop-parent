package com.shop.core.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Navigation implements Serializable{
	private Long id;
	private Date create_date;
	private Date modify_date;
	private Long version;
	private Integer orders;
	private Byte is_blank_target;
	private String name;
	private Integer position;
	private String url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public Byte getIs_blank_target() {
		return is_blank_target;
	}
	public void setIs_blank_target(Byte is_blank_target) {
		this.is_blank_target = is_blank_target;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
