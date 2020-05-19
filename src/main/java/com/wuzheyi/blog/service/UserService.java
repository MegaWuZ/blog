package com.wuzheyi.blog.service;

import com.wuzheyi.blog.po.User;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/12 14:18
 */
public interface UserService {
    User checkUser(String username,String password);
}
