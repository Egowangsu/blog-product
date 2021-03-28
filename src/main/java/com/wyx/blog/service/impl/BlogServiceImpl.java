package com.wyx.blog.service.impl;

import com.wyx.blog.dao.BlogDao;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.util.MyDate;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public List<Blog> listPage(BlogQuery blog) {   //搜索博客

        List<Blog> list= blogDao.selectAllBlogByCondition(blog);
        for(Blog l:list){
            System.out.println("实现类中的====================================");
            System.out.println(l.isPublished());
        }
        return list;
    }

    @Override
    public Integer saveBlog(Blog blog) {
        blog.setCreateTime(MyDate.getDate());
        blog.setUpdateTime(MyDate.getDate());
        blog.setViews(0);
        System.out.println(blog);
        System.out.println("save实现类中的=========================================");
        System.out.println("punlish值"+blog.isPublished());
        return blogDao.saveBlog(blog);
    }
    @Transactional
    @Override
    public int updateBlog(Integer id, Blog blog) {
        if(blogDao.getBlog(id)==null){
            throw new NotFoundException("没有找到该博客，异常警告");
        }

        blog.setUpdateTime(MyDate.getDate());
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
