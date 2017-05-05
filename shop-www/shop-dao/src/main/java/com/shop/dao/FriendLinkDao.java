package com.shop.dao;

import com.shop.core.model.FriendLink;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * Created by YuWenJing on 2017/5/6.
 */
public interface FriendLinkDao {
    @Select("SELECT id,logo,`name`,url FROM xx_friend_link LIMIT #{count}")
    List<FriendLink> findFriendLinkList(@Param(value="count")int count);
}
