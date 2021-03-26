package com.wyx.blog.domain;

import java.util.Date;

public class Son_Comment {
    private Integer id;
    private String nickName;
    private String email;
    private String content;
    private String avatar;  //头像地址
    private Date createTime;
    private Blog blog;   //一对多，多的这端加外键
    private Parent_Comment comment;  //一个回复只有一个上级对象
    public Son_Comment() {
    }

    public Son_Comment(Integer id, String nickName, String email, String content, String avatar, Date createTime, Blog blog, Parent_Comment comment) {
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
        this.blog = blog;
        this.comment = comment;
    }

    public Parent_Comment getComment() {
        return comment;
    }

    public void setComment(Parent_Comment comment) {
        this.comment = comment;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", blog=" + blog +
                '}';
    }
}
