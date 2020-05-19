package com.wuzheyi.blog.web.admin;

import com.wuzheyi.blog.po.User;
import com.wuzheyi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/12 14:50
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @RequestMapping(value = "/login")
    public String login(@RequestParam(name ="username" ,required = false) String username, @RequestParam(name ="password" ,required = false) String password, HttpSession session
    , RedirectAttributes attributes){
        if(username == null || password == null){
            attributes.addFlashAttribute("message","用户名和密码不能为空");
            return "admin/login";

        }
        User user =  userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
