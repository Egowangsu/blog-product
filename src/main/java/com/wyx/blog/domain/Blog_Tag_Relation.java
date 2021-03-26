package com.wyx.blog.domain;
/*
博客和标签的关系实体类
 */

public class Blog_Tag_Relation {
    private Integer id;
    private Integer blogId;   //博客的id
    private Integer tagId;    //标签的id

    public Blog_Tag_Relation() {
    }

    public Blog_Tag_Relation(Integer id, Integer blogId, Integer tagId) {
        this.id = id;
        this.blogId = blogId;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "Blog_Tag_Relation{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", tagId=" + tagId +
                '}';
    }
}
