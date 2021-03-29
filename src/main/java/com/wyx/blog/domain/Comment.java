package com.wyx.blog.domain;

import java.util.List;

public class Comment {
    private Integer id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;  //头像地址
    private String createTime;
    private Integer BlogId;
    private Blog blog;   //一对多，多的这端加外键
    private Integer parentCommentId;//父类的Id
    private Comment parentComment;    //父类对象
    private List<Comment> replyComment;  //所有回复他的对象集合
    public Comment() {
    }


    public Comment(Integer id, String nickName, String email, String content, String avatar, String createTime, Integer blogId, Blog blog, Integer parentCommentId, Comment parentComment, List<Comment> replyComment) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
        BlogId = blogId;
        this.blog = blog;
        this.parentCommentId = parentCommentId;
        this.parentComment = parentComment;
        this.replyComment = replyComment;
    }


    public List<Comment> getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(List<Comment> replyComment) {
        this.replyComment = replyComment;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Integer getBlogId() {
        return BlogId;
    }

    public void setBlogId(Integer blogId) {
        BlogId = blogId;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", BlogId=" + BlogId +
                ", parentCommentId=" + parentCommentId +
                ", parentComment=" + parentComment +
                ", replyComment=" + replyComment +
                '}';
    }
}
