package com.wuzheyi.blog.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.po.Type;
import com.wuzheyi.blog.service.BlogService;
import com.wuzheyi.blog.service.TypeService;
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
 * @date 2020/4/19 18:32
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllType();
        //-1表示从首页导航点进来的
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<IndexBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

}
