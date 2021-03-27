package com.wyx.blog.service;

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
}
