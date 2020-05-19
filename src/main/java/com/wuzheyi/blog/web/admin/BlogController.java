package com.wuzheyi.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.dao.BlogMapper;
import com.wuzheyi.blog.po.Blog;
import com.wuzheyi.blog.po.User;
import com.wuzheyi.blog.service.BlogService;
import com.wuzheyi.blog.service.TagService;
import com.wuzheyi.blog.service.TypeService;
import com.wuzheyi.blog.service.UserService;
import com.wuzheyi.blog.vo.BlogQuery;
import com.wuzheyi.blog.vo.SearchBlog;
import com.wuzheyi.blog.vo.ShowBlog;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/12 15:53
 */
@Controller
@RequestMapping("/admin")
public class BlogController {




    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
    }
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<BlogQuery> list = blogService.getAllBlogs();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input")
    public String addBlogs(Model model){
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //新增
    @PostMapping("/blogs")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getTypeId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "新增成功");
        return "redirect:/admin/blogs";
    }

    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        int isDeleted = blogService.deleteBlog(id);
        if(isDeleted == 1){
            attributes.addFlashAttribute("message","删除成功");
        }else {
            attributes.addFlashAttribute("message","删除失败");
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String update(@PathVariable Long id,Model model){
        ShowBlog blog = blogService.getBlogById(id);
        setTypeAndTag(model);
        model.addAttribute("blog",blog);
        return "admin/blogs-update";
    }

    @PostMapping("/blogs/update")
    public String editPost(ShowBlog showBlog,RedirectAttributes attributes) {
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String searchBlog(SearchBlog searchBlog,Model model,@RequestParam(defaultValue = "1",value = "pageNum")
                            Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<BlogQuery> list = blogService.searchBlogs(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message","查询成功");
        return "admin/blogs";
    }
}
