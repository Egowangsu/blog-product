package com.wyx.blog.service.impl;

import com.wyx.blog.dao.BlogDao;
import com.wyx.blog.dao.TagDao;
import com.wyx.blog.domain.Blog;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.BlogService;
import com.wyx.blog.util.MarkdownUtils;
import com.wyx.blog.util.MyBeanUtils;
import com.wyx.blog.util.MyDate;
import com.wyx.blog.vo.BlogQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogDao blogDao;
    @Resource
    private TagDao tagDao;
    @Override
    public Blog getBlog(Integer id) {
       return  blogDao.getBlog(id);

    }

    @Override
    public List<Blog> listPage(BlogQuery blog) {   //搜索博客

        List<Blog> list= blogDao.selectAllBlogByCondition(blog);
        return list;
    }

    @Override
    public Integer saveBlog(Blog blog) {
        blog.setCreateTime(MyDate.getDate());
        blog.setUpdateTime(MyDate.getDate());
        blog.setViews(0);
        blog.setYear(blog.getUpdateTime().substring(0,4));
        System.out.println(blog);
        return blogDao.saveBlog(blog);
    }
    @Transactional
    @Override
    public int updateBlog(Integer id, Blog blog) {
        if(blogDao.getBlog(id)==null){
            throw new NotFoundException("没有找到该博客，异常警告");
        }

        blog.setUpdateTime(MyDate.getDate());
        blog.setViews(0);
        return blogDao.updateBlog(id,blog);
    }
    @Transactional
    @Override
    public void deleteBlog(Integer id) {
            blogDao.deleteBlog(id);
            tagDao.deleteTagAndBlogRelation(id);

    }

    @Override
    public Integer getCount() {
        return blogDao.getCount();
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Override
    public List<Blog> listBlogTop(Integer size) {
        return blogDao.listBlogTop(size);
    }

    @Override
    public List<Blog> getBlogsBySearch(String query) {
        return blogDao.getBlogsBySearch(query);
    }

    @Override
    public Blog getAndConvert(Integer id) {  //格式转换，将数据库中markdown格式的博客内容拿出来，转换成html格式展示页面
        Blog blog=blogDao.getBlog(id);
        if (blog==null){
            throw new NotFoundException("博客不存在");
        }
        Blog b = new Blog();
        MyBeanUtils.copyPropertiesIgnoreNull(blog, b);  //copy blog的值到b,为了防止修改元数据库中的信息
        String content =b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return b;
    }

    @Transactional
    @Override
    public void updateViews(Integer id) {
        blogDao.updateViews(id);
    }

    @Override
    public List<Blog> getBlogByTypeId(Integer id) {
        return blogDao.getBlogByTypeId(id);
    }

    @Override
    public List<Blog> getBlogByTagId(Integer id) {
        return blogDao.getBlogByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBLog() {
        //先拿到全部年份
        List<String> yearList=blogDao.getYear();
        Map<String,List<Blog>> archiveMap=new HashMap<>();
        for(String year:yearList){
            List<Blog> list=blogDao.getBlogByYear(year);
            for(Blog blog:list){
                blog.setUpdateTime2(blog.getUpdateTime().substring(0, 10));
            }
            archiveMap.put(year,list);
        }
        return archiveMap;
    }

    @Override
    public Integer getBlogCount() {
        return blogDao.getBlogCount();
    }
}
