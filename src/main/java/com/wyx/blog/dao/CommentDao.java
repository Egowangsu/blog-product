package com.wyx.blog.dao;

import com.wyx.blog.domain.Comment;

import java.util.List;

public interface CommentDao {
  Comment getParentComment(Integer parentCommentId);

    List<Comment> listCommentsByBlogId(Integer blogId);

    Comment getCommentById(Integer parentCommentId);

    int saveComment(Comment comment);

    List<Comment> getSonCommentByParentId(Integer id);
}
