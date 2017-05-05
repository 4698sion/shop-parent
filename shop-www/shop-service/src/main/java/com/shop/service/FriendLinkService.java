package com.shop.service;

import com.shop.core.model.FriendLink;
import com.shop.dao.FriendLinkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YuWenJing on 2017/5/6.
 */
@Service
public class FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

    public List<FriendLink> findFriendLinkList(int count){
        List<FriendLink> friendLinks=friendLinkDao.findFriendLinkList(count);
        return friendLinks;
    }

}
