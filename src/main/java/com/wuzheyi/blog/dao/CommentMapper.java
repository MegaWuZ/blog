package com.wuzheyi.blog.dao;

import com.wuzheyi.blog.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //添加一个评论
    int saveComment(Comment comment);

    //查询父级评论
    List<Comment> findByParentIdNull(@Param("BlogId") Long BlogId,@Param("ParentId") Long ParentId);

    //查询一级回复
    List<Comment> findParentIdNotNull(@Param("id") Long id);

    //查询二级以及所有子集回复
    List<Comment> findByReplayId(@Param("childId") Long childId);
}