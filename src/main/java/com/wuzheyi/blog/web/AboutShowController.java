package com.wuzheyi.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/20 11:34
 */
@Controller
public class AboutShowController {
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
