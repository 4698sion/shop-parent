package com.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shop.core.base.exception.ParamException;
import com.shop.core.dto.UserDto;
import com.shop.core.model.User;
import com.shop.dao.UserDao;

@Service
public class UserService {
	
	
	@Autowired
	private UserDao userDao;
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	public Integer addUser(User user) {
		// 基本参数验证
		// TODO
		Integer count = userDao.insert(user);
		Integer userId = user.getId();
		logger.info("插入影响的行数：count={}，返回的主键：userId={}", count, userId);
		return userId;
	}
	
	
	public User findById(Integer id) {
		// 基本参数验证
		if (id == null || id < 1) {
			throw new ParamException("请选择用户进行查询");
		}
		User user = userDao.findById(id);
		if (user == null) {
			throw new ParamException("该用户不存在");
		}
		return user;
	}
		
	public PageList<User> selectForPage(UserDto userDto) {
		
		PageBounds pageBounds = userDto.toPageBounds(); // 构造出pagebounds
		
		PageList<User> result = (PageList<User>) userDao.selectForPage(userDto.getUname(), pageBounds);
		logger.info("paginator:{}", result.getPaginator());
		
		return result;
	}
	
	
}
