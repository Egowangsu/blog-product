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
}
