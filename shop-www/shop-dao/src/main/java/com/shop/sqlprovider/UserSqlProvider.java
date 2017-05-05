package com.shop.sqlprovider;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSqlProvider {
	
	private static Logger logger = LoggerFactory.getLogger(UserSqlProvider.class);
	
	public String findByUserName(String userName) {
		
		SQL sql = new SQL();
		sql.SELECT("id, user_name, password, gender").FROM("t_user").WHERE("1=1");
		if (userName != null && userName.trim().length() > 0) {
			sql.AND().WHERE("user_name = #{userName}");
		}
		logger.info("执行的sql:{}", sql.toString());
		return sql.toString();
	}
	
	
}
