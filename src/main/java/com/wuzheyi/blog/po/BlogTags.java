package com.wuzheyi.blog.po;

public class BlogTags {
    private Integer id;

    private Long tagId;

    private String blogId;

    public BlogTags(Integer id, Long tagId, String blogId) {
        this.id = id;
        this.tagId = tagId;
        this.blogId = blogId;
    }

    public BlogTags() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }
}