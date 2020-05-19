package com.wuzheyi.blog.web;

import com.wuzheyi.blog.po.Comment;
import com.wuzheyi.blog.po.User;
import com.wuzheyi.blog.service.BlogService;
import com.wuzheyi.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.lang.Thread;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/20 13:10
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listComment(blogId);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, RedirectAttributes attributes) throws Exception {
        Long blogId = comment.getBlogId();
        //set Blog
        comment.setBlog(blogService.getDetailedBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setNickname(user.getNickname());
            comment.setEmail(user.getEmail());
            comment.setAvatar(user.getAvatar());
            comment.setAdmin(1);
        } else {
            //设置头像
            comment.setAdmin(0);
            comment.setAvatar(avatar);
        }

        if (comment.getParentComment().getId() != null) {
            Long parentCommentId = comment.getParentComment().getId();
            comment.setParentCommentId(parentCommentId);
            if(comment.getAdmin() == 1){
                Comment parentComment = commentService.getComment(parentCommentId);
                commentService.sendEmail(comment,parentComment);

                TimeUnit.MILLISECONDS.sleep(3000);

            }

        }
        commentService.saveComment(comment);
        return "redirect:/blog/" + blogId;
    }
}
