package com.wyx.blog.service;

import com.wyx.blog.domain.Blog;
import com.wyx.blog.vo.BlogQuery;

import java.util.List;

public interface BlogService {
    Blog getBlog(Integer id);

    List<Blog> listPage(BlogQuery blog);

    int saveBlog(Blog blog);

    int updateBlog(Integer id,Blog blog);

    void deleteBlog(Integer id);
}
