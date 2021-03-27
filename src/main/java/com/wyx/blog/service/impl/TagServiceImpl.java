package com.wyx.blog.service.impl;

import com.wyx.blog.dao.TagDao;
import com.wyx.blog.domain.Tag;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;


    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }
    @Transactional
    @Override
    public Tag getTag(Integer id) {
       return tagDao.getTag(id);
    }

    @Override
    public List<Tag> listPage() {
        return tagDao.selectAllTag();
    }
    @Transactional
    @Override
    public int updateTag( Tag tag,Integer id) {

        Tag t= tagDao.getTag(id);  //更新前先查询
        if(t==null){
            throw new NotFoundException("更新失败，没有找到该标签");
        }
        return tagDao.updateTag(tag.getName(), id);
    }
    @Transactional
    @Override
    public int deleteTag(Integer id) {
        tagDao.deleteTag(id);
        return 0;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }
}
