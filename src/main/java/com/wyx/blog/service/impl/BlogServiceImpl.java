package com.wyx.blog.service.impl;

import com.wyx.blog.dao.BlogDao;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;
    @Override
    public Blog getBlog(Integer id) {
       return  blogDao.getBlog(id);

    }

    @Override
    public List<Blog> listPage(BlogQuery blog) {
        return blogDao.selectAllBlogByCondition(blog);
    }
    @Transactional
    @Override
    public Integer saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        System.out.println(blog);
        return blogDao.saveBlog(blog);
    }
    @Transactional
    @Override
    public int updateBlog(Integer id, Blog blog) {
        if(blogDao.getBlog(id)==null){
            throw new NotFoundException("没有找到该博客，异常警告");
        }
        return blogDao.updateBlog(id,blog);
    }
    @Transactional
    @Override
    public void deleteBlog(Integer id) {
            blogDao.deleteBlog(id);
    }

    @Override
    public Integer getCount() {
        return blogDao.getCount();
    }
}
