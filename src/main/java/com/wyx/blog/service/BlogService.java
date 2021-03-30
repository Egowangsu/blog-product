package com.wyx.blog.service;

import com.wyx.blog.domain.Blog;
import com.wyx.blog.vo.BlogQuery;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Integer id);

    List<Blog> listPage(BlogQuery blog);

    Integer saveBlog(Blog blog);

    int updateBlog(Integer id,Blog blog);

    void deleteBlog(Integer id);

    Integer getCount();

    List<Blog> getAllBlogs();

    List<Blog> listBlogTop(Integer size);

    List<Blog> getBlogsBySearch(String query);
    Blog getAndConvert(Integer id);  //转换博客内容格式

    void updateViews(Integer id);

    List<Blog> getBlogByTypeId(Integer id);

    List<Blog> getBlogByTagId(Integer id);

    Map<String,List<Blog>> archiveBLog();

    Integer getBlogCount();
}
