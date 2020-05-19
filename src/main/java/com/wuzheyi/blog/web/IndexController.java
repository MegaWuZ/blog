package com.wuzheyi.blog.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.po.Comment;
import com.wuzheyi.blog.po.Tag;
import com.wuzheyi.blog.po.Type;
import com.wuzheyi.blog.service.BlogService;
import com.wuzheyi.blog.service.CommentService;
import com.wuzheyi.blog.service.TagService;
import com.wuzheyi.blog.service.TypeService;
import com.wuzheyi.blog.vo.DetailedBlog;
import com.wuzheyi.blog.vo.IndexBlog;
import com.wuzheyi.blog.vo.RecommendedBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/12 7:55
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @Autowired
    TypeService typeService;
    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public String blog(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<IndexBlog> list =  blogService.searchIndexBlogs();


        List<Type> allType = typeService.getAllType();
        List<Tag> allTag = tagService.getAllTag();
        List<RecommendedBlog> recommendedBlogs = blogService.getRecommendedBlogs();
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        model.addAttribute("recommendedBlogs",recommendedBlogs);
        return "index";
    }

    @RequestMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        blogService.updateView(id);
        List<Comment> comments = commentService.listComment(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 100);
        List<IndexBlog> searchBlog = blogService.getSearchBlog(query);

        PageInfo<IndexBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}
