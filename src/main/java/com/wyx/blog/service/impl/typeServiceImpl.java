package com.wyx.blog.service.impl;

import com.wyx.blog.dao.TypeDao;
import com.wyx.blog.domain.Type;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.TypeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class typeServiceImpl implements TypeService {
    @Resource
    private TypeDao typeDao;


    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }
    @Transactional
    @Override
    public Type getType(Integer id) {
       return typeDao.getType(id);
    }

    @Override
    public List<Type> listPage() {
        return typeDao.selectAllType();
    }
    @Transactional
    @Override
    public int updateType(Integer id, Type type) {

        Type t= typeDao.getType(id);  //更新前先查询
        if(t==null){
            throw new NotFoundException("更新失败，没有找到该标签");
        }
        return typeDao.updateType(id, type);
    }
    @Transactional
    @Override
    public void deleteType(Integer id) {
        typeDao.deleteType(id);
    }
}
