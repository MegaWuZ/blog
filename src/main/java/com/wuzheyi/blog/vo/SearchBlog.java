package com.wuzheyi.blog.vo;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/17 16:02
 */
public class SearchBlog {
    String title;
    Long typeId;
    Boolean recommend;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }
}
