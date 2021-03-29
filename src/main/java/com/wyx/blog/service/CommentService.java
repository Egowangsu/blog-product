package com.wyx.blog.service;

import com.wyx.blog.domain.Comment;

import java.util.List;

public interface CommentService {
    //根据博客id查询他的评论
    List<Comment> listCommentsByBlogId(Integer blogId);
    //保存评论
    int saveComment(Comment comment);
    Comment getCommentById(Integer parentCommentId);

    Comment getParentComment(Integer parentCommentId);
}
