package com.wyx.blog.service.impl;

import com.wyx.blog.dao.UserDao;
import com.wyx.blog.domain.User;
import com.wyx.blog.service.UserService;
import com.wyx.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User checkUser(String username, String password) {
        User user=userDao.checkUser(username, MD5Util.getMD5(password));
        return user;
    }

    @Override
    public User getUser() {
        return userDao.getUser();
    }
}
