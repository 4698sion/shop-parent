package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shop.core.model.User;

public interface UserDao {
	
	@Insert("insert into user (uname, pwd, nation, location) "
			+ "values (#{uname}, #{pwd}, #{nation}, #{location})")
	@Options(useGeneratedKeys = true, keyProperty="id") // 返回插入的主键值
	Integer insert(User user);
	
	@Select("select uname, pwd, nation, location from user where id = #{id}")
	User findById(@Param(value="id")Integer id);
	
	@Select("select * from user")
	List<User> selectForPage(@Param(value="uname")String uname, PageBounds pageBounds);
	
}