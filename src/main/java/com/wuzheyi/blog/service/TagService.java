package com.wuzheyi.blog.service;

import com.wuzheyi.blog.po.Tag;

import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/14 22:43
 */
public interface TagService {
    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagByString(String text);

    List<Tag> getAdminTag();
}
