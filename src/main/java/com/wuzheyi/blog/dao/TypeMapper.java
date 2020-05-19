package com.wuzheyi.blog.dao;

import com.wuzheyi.blog.po.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> getAdminType();

    List<Type> getAllType();

    Type getTypeByName(String name);
}