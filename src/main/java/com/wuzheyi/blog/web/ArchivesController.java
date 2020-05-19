package com.wuzheyi.blog.web;

import com.wuzheyi.blog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/20 23:58
 */
@Controller
public class ArchivesController {

    @Autowired
    ArchiveService archiveService;

    @GetMapping("/archives")
    public String getArchives(Model model){
        model.addAttribute("blogCount",archiveService.count());
        model.addAttribute("archiveMap",archiveService.getArchiveBlogs());
        return "archives";
    }
}
