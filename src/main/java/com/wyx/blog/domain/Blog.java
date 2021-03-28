package com.wyx.blog.domain;

import java.util.Date;
import java.util.List;

public class Blog {
private Integer id;    //编号
private String title;   //标题
private String content;  //内容
private String picture;  //图片地址
private Integer flag;   //标签
private Integer views;   //浏览次数b
private boolean appreciate;  // 是否开启赞赏
private boolean shareStatement;  //是否开启分享
private boolean commentabled;  //是否开启评论
private boolean published;  //是发布还是保存草稿
private boolean recommend;   //是否推荐
private String createTime;  //创建时间
private String updateTime;    //跟新时间
private String description;  //简短描述
private String typeId;   //多对一，多的那端加外键
private String userId;
private List<Tag> tag;
private String tagIds;

    public Blog() {
    }


    public Blog(Integer id, String title, String content, String picture, Integer flag, Integer views, boolean appreciate, boolean shareStatement, boolean commentabled, boolean published, boolean recommend, String createTime, String updateTime, String description, String typeId, String userId, List<Tag> tag, String tagIds) {
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
        this.description = description;
        this.typeId = typeId;
        this.userId = userId;
        this.tag = tag;
        this.tagIds = tagIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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


    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public boolean isAppreciate() {
        return appreciate;
    }

    public void setAppreciate(boolean appreciate) {
        this.appreciate = appreciate;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", flag=" + flag +
                ", views=" + views +
                ", appreciate=" + appreciate +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", description='" + description + '\'' +
                ", typeId='" + typeId + '\'' +
                ", userId='" + userId + '\'' +
                ", tag=" + tag +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }
}
