package com.wuzheyi.blog.service;

import com.wuzheyi.blog.vo.ArchiveBlog;

import java.util.List;
import java.util.Map;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/21 0:05
 */
public interface ArchiveService {
    Map<Integer, List<ArchiveBlog>> getArchiveBlogs();

    int count();
}
