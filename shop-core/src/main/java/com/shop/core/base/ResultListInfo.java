package com.shop.core.base;

import java.io.Serializable;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

@SuppressWarnings("serial")
public class ResultListInfo<E> implements Serializable {
	
	private BaseDto baseDto; // 回显
	private List<E> results; // 数据
	private PageInfo pageInfo; // 分页对象
	private Paginator paginator; // 分页对象
	private int resultCode; // 返回码
	private String resultMessage; // 返回消息
	
	
	public BaseDto getBaseDto() {
		return baseDto;
	}
	public void setBaseDto(BaseDto baseDto) {
		this.baseDto = baseDto;
	}
	public List<E> getResults() {
		return results;
	}
	public void setResults(List<E> results) {
		this.results = results;
	}
	
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Paginator getPaginator() {
		return paginator;
	}
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
}
