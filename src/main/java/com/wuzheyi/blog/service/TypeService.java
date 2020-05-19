package com.wuzheyi.blog.service;

import com.wuzheyi.blog.po.Type;

import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/13 10:37
 */
public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    int updateType(Type type);

    int deleteType(Long id);

    Type getTypeByName(String name);

    List<Type> getAdminType();
}
