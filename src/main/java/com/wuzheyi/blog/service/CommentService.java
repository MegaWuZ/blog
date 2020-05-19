package com.wuzheyi.blog.service;

import com.wuzheyi.blog.po.Comment;
import org.springframework.scheduling.annotation.Async;

import java.net.UnknownHostException;
import java.util.List;


/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/19 9:48
 */
public interface CommentService {
    //查询评论列表
    List<Comment> listComment(Long blogId);

    //保存评论

    int saveComment(Comment comment);


    void sendEmail(Comment comment,Comment parentComment) throws UnknownHostException;

    Comment getComment(Long commentId);
}
