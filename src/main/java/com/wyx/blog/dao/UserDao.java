package com.wyx.blog.dao;

import com.wyx.blog.domain.User;

public interface UserDao {

    User checkUser(String username, String password);   //检测用户是否合法
}
