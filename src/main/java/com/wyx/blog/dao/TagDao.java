package com.wyx.blog.dao;

import com.wyx.blog.domain.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagDao {
    int saveTag(Tag tag);

    Tag getTag(Integer id);

    int updateTag(@Param("name") String name, @Param("id") Integer id);

    void deleteTag(Integer id);

    List<Tag> selectAllTag();

    Tag getTagByName(String name);

    void addTagAndBlogRelation(@Param("blogId") Integer blogId,@Param("res") String res);

    String[] getTagIds(Integer id);

    void deleteTagAndBlogRelation(Integer id);
}
