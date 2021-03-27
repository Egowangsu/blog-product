package com.wyx.blog.domain;

import java.util.Date;
public class Blog {
private Integer id;    //编号
private String title;   //标题
private String content;  //内容
private String picture;  //图片地址
private String flag;   //标签
private Integer views;   //浏览次数b
private String appreciate;  // 是否开启赞赏
private String shareStatement;  //是否开启分享
private String commentabled;  //是否开启评论
private String published;  //是发布还是保存草稿
private String recommend;   //是否推荐
private Date createTime;  //创建时间
private Date updateTime;    //跟新时间
private Integer typeId;   //多对一，多的那端加外键
private Integer userId;
    public Blog() {
    }

    public Blog(Integer id, String title, String content, String picture, String flag, Integer views, String appreciate, String shareStatement, String commentabled, String published, String recommend, Date createTime, Date updateTime, Integer typeId, Integer userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.picture = picture;
        this.flag = flag;
        this.views = views;
        this.appreciate = appreciate;
        this.shareStatement = shareStatement;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeId = typeId;
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getAppreciate() {
        return appreciate;
    }

    public void setAppreciate(String appreciate) {
        this.appreciate = appreciate;
    }

    public String getShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(String shareStatement) {
        this.shareStatement = shareStatement;
    }

    public String getCommentabled() {
        return commentabled;
    }

    public void setCommentabled(String commentabled) {
        this.commentabled = commentabled;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciate=" + appreciate +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", userId=" + userId +
                '}';
    }
}
