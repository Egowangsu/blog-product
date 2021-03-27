package com.wyx.blog.vo;

public class BlogQuery {
    private String title;
    private Integer typeId;
    private String recommend;

    public BlogQuery() {
    }

    public BlogQuery(String title, Integer typeId, String recommend) {
        this.title = title;
        this.typeId = typeId;
        this.recommend = recommend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
