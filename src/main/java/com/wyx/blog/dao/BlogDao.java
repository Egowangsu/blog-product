package com.wyx.blog.dao;

import com.wyx.blog.domain.Blog;
import com.wyx.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogDao {
    Blog getBlog(Integer id);

    List<Blog> selectAllBlogByCondition(@Param("blog") BlogQuery blog);

    Integer saveBlog( Blog blog);

    int updateBlog(Integer id, @Param("blog") Blog blog);

    void deleteBlog(Integer id);

    Integer getCount();
}
