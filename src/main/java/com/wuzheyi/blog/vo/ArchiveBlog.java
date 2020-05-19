package com.wuzheyi.blog.vo;

import java.util.Date;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/21 0:06
 */
public class ArchiveBlog {
    private Long id;
    private String title;
    private Date createTime;
    String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
