package com.wuzheyi.blog.po;

import java.util.ArrayList;
import java.util.List;

public class Type {
    private Long id;

    private String name;
    private List<Blog> blogs = new ArrayList<>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Type() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}