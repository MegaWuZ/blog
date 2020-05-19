package com.wuzheyi.blog.dao;

import com.wuzheyi.blog.po.Blog;
import com.wuzheyi.blog.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<BlogQuery> getAllBlogs();

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    List<BlogQuery> searchBlogs(SearchBlog searchBlog);

    ArrayList<IndexBlog> searchIndexBlogs();

    ArrayList<IndexBlog> getByTypeId(Long typeId);

    ArrayList<IndexBlog> getByTagId(Long tagId);

    ArrayList<RecommendedBlog> getRecommendedBlogs();

    DetailedBlog getDetailedBlog(Long id);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int deleteBlogAndTag(Long BlogId);

    int updateView(Long id);

    List<ArchiveBlog> getArchiveBlogs(int year);

    int count();

    List<IndexBlog> getSearchBlog(String query);
}