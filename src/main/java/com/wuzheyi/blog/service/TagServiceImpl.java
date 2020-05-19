package com.wuzheyi.blog.service;

import com.wuzheyi.blog.dao.TagMapper;
import com.wuzheyi.blog.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/14 22:43
 */
@Service
public class TagServiceImpl implements TagService {


    @Autowired
    TagMapper tagMapper;
    @Override
    public int saveTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagMapper.selectByName(name);
    }

    @Override
    public List<Tag> getAdminTag() {
        return tagMapper.getAdminTag();
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagMapper.selectByPrimaryKey(aLong));
        }
        return tags;
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {

                list.add(Long.parseLong(idarray[i]));
            }
        }
        return list;
    }
}
