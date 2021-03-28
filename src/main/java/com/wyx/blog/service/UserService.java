package com.wyx.blog.service;

import com.wyx.blog.domain.User;

public interface UserService {
    User checkUser(String username, String password);

    User getUser();
}
