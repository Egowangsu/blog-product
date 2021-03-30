package com.wyx.blog.service;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.wyx.blog.domain.Tag;

import java.util.List;

public interface TagService {
        //新增Tag
    int saveTag(Tag tag);
        //查询Tag
    Tag getTag(Integer id);
        //分页
    List<Tag> listPage();
        //修改Tag
    int updateTag(Tag tag, Integer id);
     //删除Tag
    int deleteTag(Integer id);

    Tag getTagByName(String name);

    void addTagAndBlogRelation(Integer blogId, String res);

    String[] getIds(Integer id);

    void deleteTagAndBlogRelation(Integer id);
    //根据博客数量选取最多的前i个标签
    List<Tag> listTagTop(Integer i);
    List<Tag> getTagList(Integer id);

}
