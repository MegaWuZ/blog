package com.wuzheyi.blog.dao;

import com.wuzheyi.blog.po.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);

    Tag selectByName(String name);

    List<Tag> getAdminTag();

    List<Tag> getAllTag();
}