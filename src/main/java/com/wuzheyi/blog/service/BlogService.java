package com.wuzheyi.blog.service;

import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.po.Blog;
import com.wuzheyi.blog.vo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/14 23:23
 */
public interface BlogService {
    Blog getBlog(Long id);

    List<BlogQuery> getAllBlogs();

    int saveBlog(Blog blog);

    int updateBlog(Long id,Blog blog);

    int deleteBlog(Long id);

    ShowBlog getBlogById(Long id);

    ArrayList<IndexBlog>  getByTypeId(Long typeID);

    ArrayList<IndexBlog> getByTagId(Long tagId);

    int updateBlog(ShowBlog showBlog);

    List<BlogQuery> searchBlogs(SearchBlog searchBlog);

    ArrayList<IndexBlog> searchIndexBlogs();

    ArrayList<RecommendedBlog> getRecommendedBlogs();

    DetailedBlog  getDetailedBlog(Long id);

    int updateView(Long id);

    List<IndexBlog> getSearchBlog(String query);
}
