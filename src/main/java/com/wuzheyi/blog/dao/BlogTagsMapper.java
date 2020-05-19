package com.wuzheyi.blog.dao;

import com.wuzheyi.blog.po.BlogTags;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogTagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogTags record);

    int insertSelective(BlogTags record);

    BlogTags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogTags record);

    int updateByPrimaryKey(BlogTags record);
}