package com.wuzheyi.blog.service;

import com.wuzheyi.blog.dao.UserMapper;
import com.wuzheyi.blog.po.User;
import com.wuzheyi.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/12 14:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.login(username, MD5Utils.code(password));
        return user;
    }
}
