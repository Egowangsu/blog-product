package com.wyx.blog.dao;

import com.wyx.blog.domain.Blog;
import com.wyx.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogDao {
    Blog getBlog(Integer id);

    List<Blog> selectAllBlogByCondition(BlogQuery blog);

    Integer saveBlog( Blog blog);

    int updateBlog(Integer id, @Param("blog") Blog blog);

    void deleteBlog(Integer id);

    Integer getCount();

    List<Blog> getAllBlogs();

    List<Blog> listBlogTop(Integer size);  //根据更新时间推荐最新的博客

    List<Blog> getBlogsBySearch(String query);
}
