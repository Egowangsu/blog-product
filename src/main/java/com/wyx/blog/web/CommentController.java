package com.wyx.blog.web;

import com.wyx.blog.domain.Comment;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.service.CommentService;
import com.wyx.blog.util.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Value(value = "/images/userhead.jpg")
    private String avatar;
    @RequestMapping("/comments/{blogId}")   //将结果返回给前端
    public String comment(@PathVariable("blogId") Integer blogId, Model model){
        List<Comment> list=commentService.listCommentsByBlogId(blogId);
        for (Comment c:list) {     //把每个子节点的父节点赋值上去
            List<Comment> list2=c.getReplyComment();
            for(Comment c2:list2){
                c2.setParentComment(commentService.getParentComment(c2.getParentCommentId()));
            }
            System.out.println(c
            );
        }
        model.addAttribute("comments",list);  //根据博客id返回他的全部评论
        return "blogDetail::commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        Integer blogId=comment.getBlogId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setCreateTime(MyDate.getDate());  //给一个创建的时间
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
            return"redirect:/comments/"+comment.getBlog().getId();  //保存结果后将id值带上传到上面那个方法
    }



}
