package com.shop.core.util;

import org.springframework.beans.BeanUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.shop.core.base.BaseDto;
import com.shop.core.base.PageInfo;
import com.shop.core.base.ResultListInfo;

public class ResultListInfoUtil<E> {
	
	public ResultListInfo<E> buildSuccessResultList(PageList<E> pageList, BaseDto baseDto) {
		ResultListInfo<E> result = new ResultListInfo<>();
		result.setBaseDto(baseDto);
		Paginator paginator = pageList.getPaginator();
		PageInfo pageInfo = new PageInfo();
		BeanUtils.copyProperties(paginator, pageInfo);
		result.setPageInfo(pageInfo);
		result.setPaginator(paginator);
		result.setResults(pageList);
		result.setResultCode(1);
		result.setResultMessage("success");
		return result;
	}
}
