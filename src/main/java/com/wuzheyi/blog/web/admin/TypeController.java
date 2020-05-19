package com.wuzheyi.blog.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzheyi.blog.po.Type;
import com.wuzheyi.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/13 11:01
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<Type> allType = typeService.getAdminType();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    //到修改页面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        System.out.println(id);
        Type type = typeService.getType(id);
        System.out.println(type);
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    @PostMapping("/types/add")
    public String Add(Type type, BindingResult result, RedirectAttributes attributes) {
        System.out.println("前端传过来的表单" + type);
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        }
        //添加操作
        typeService.saveType(type);
        return "redirect:/admin/types";
    }




    //进行修改
    @PostMapping("/types/update")
    public String editPost(Type type) {
        System.out.println(type);
        typeService.updateType(type);
        return "redirect:/admin/types";
    }


    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }


}

