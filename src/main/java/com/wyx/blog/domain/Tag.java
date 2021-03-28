package com.wyx.blog.domain;

public class Tag {
    private Integer id;
    private String name;
    private Integer blogNums;
    public Tag() {
    }

    public Tag(Integer id, String name, Integer blogNums) {
        this.id = id;
        this.name = name;
        this.blogNums = blogNums;
    }

    public Integer getBlogNums() {
        return blogNums;
    }

    public void setBlogNums(Integer blogNums) {
        this.blogNums = blogNums;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogNums=" + blogNums +
                '}';
    }
}
