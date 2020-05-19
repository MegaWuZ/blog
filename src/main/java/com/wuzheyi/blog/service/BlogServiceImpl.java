package com.wuzheyi.blog.service;

import com.wuzheyi.NotFoundException;
import com.wuzheyi.blog.dao.BlogMapper;
import com.wuzheyi.blog.po.Blog;
import com.wuzheyi.blog.po.Tag;
import com.wuzheyi.blog.util.MarkdownUtils;
import com.wuzheyi.blog.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/14 23:44
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.insert(blog);
    }

    @Override
    public int updateBlog(Long id, Blog blog) {
        blogMapper.deleteBlogAndTag(id);
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogMapper.deleteBlogAndTag(id);
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public ArrayList<IndexBlog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }

    @Override
    public ArrayList<IndexBlog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        blogMapper.deleteBlogAndTag(showBlog.getId());
        //将标签的数据存到t_blogs_tag表中
        String tags = showBlog.getTagIds();
        String[] tagIds = tags.split(",");

        BlogAndTag blogAndTag = null;
        for (String tag : tagIds) {
           blogAndTag = new BlogAndTag(Long.parseLong(tag),showBlog.getId());
           blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(showBlog);
    }

    @Override
    public List<BlogQuery> searchBlogs(SearchBlog searchBlog) {
        return blogMapper.searchBlogs(searchBlog);
    }

    @Override
    public ArrayList<IndexBlog> searchIndexBlogs() {
        return blogMapper.searchIndexBlogs();
    }

    @Override
    public ArrayList<RecommendedBlog> getRecommendedBlogs() {
        return blogMapper.getRecommendedBlogs();
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogMapper.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public int updateView(Long id) {
        return blogMapper.updateView(id);
    }

    @Override
    public List<IndexBlog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }


}
