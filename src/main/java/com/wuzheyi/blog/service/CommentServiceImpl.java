package com.wuzheyi.blog.service;


import com.wuzheyi.blog.dao.CommentMapper;
import com.wuzheyi.blog.po.Comment;
import com.wuzheyi.blog.util.SendmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wuzheyi
 * @version 1.0
 * @date 2020/4/19 19:22
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    /**
     * @Description: 查询评论
     * @Auther: ONESTAR
     * @Date: 17:26 2020/4/14
     * @Param:
     * @Return: 评论消息
     */
    @Override
    public List<Comment> listComment(Long blogId) {
        //查询出父节点
        List<Comment> comments = commentMapper.findByParentIdNull(blogId,Long.parseLong("-1"));
        for(Comment comment : comments){
            Long id = comment.getId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.findParentIdNotNull(id);
            //查询出子评论
            combineChildren(childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    /**
     * @Description: 查询出子评论
     * @Auther: ONESTAR
     * @Date: 17:31 2020/4/14
     * @Param: childComments：所有子评论
     * @Param: parentNickname1：父评论的姓名
     * @Return:
     */
    private void combineChildren(List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子回复
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Auther: ONESTAR
     * @Date: 17:33 2020/4/14
     * @Param: childId：子评论的id
     * @Param: parentNickname1：子评论的姓名
     * @Return:
     */
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByReplayId(childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }

    @Override
    //存储评论信息
    public int saveComment(Comment comment) {

        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }

    @Override
    public void sendEmail(Comment comment,Comment parentComment) throws UnknownHostException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://").append("www.wuzheyi.cn").append("/blog/").append(comment.getBlogId());
        String content = stringBuilder.toString();
        try {
            SendmailUtil.sendEmail(parentComment.getEmail(),"博主回复您啦，快去看看"
                    ,content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Comment getComment(Long commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }
}
