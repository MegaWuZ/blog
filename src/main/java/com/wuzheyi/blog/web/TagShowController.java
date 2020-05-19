package com.wuzheyi.blog.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.po.Tag;
import com.wuzheyi.blog.service.BlogService;
import com.wuzheyi.blog.service.TagService;
import com.wuzheyi.blog.vo.IndexBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/19 20:53
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.getAllTag();
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        List<IndexBlog> blogs = blogService.getByTagId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
