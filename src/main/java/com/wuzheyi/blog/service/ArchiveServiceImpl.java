package com.wuzheyi.blog.service;

import com.wuzheyi.blog.dao.BlogMapper;
import com.wuzheyi.blog.vo.ArchiveBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/21 0:05
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public Map<Integer, List<ArchiveBlog>> getArchiveBlogs() {
        Map<Integer,List<ArchiveBlog>> map = new LinkedHashMap<>();
        Calendar date = Calendar.getInstance();
        Integer year = date.get(Calendar.YEAR);
        for(int i = year; i >= 2019; i--){
            map.put(i,blogMapper.getArchiveBlogs(i));
        }
        return map;
    }

    @Override
    public int count() {
        return blogMapper.count();
    }
}
