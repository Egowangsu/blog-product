package com.wyx.blog.service.impl;

import com.wyx.blog.dao.TypeDao;
import com.wyx.blog.domain.Type;
import com.wyx.blog.exception.NotFoundException;
import com.wyx.blog.service.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
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
    public int updateType( Type type,Integer id) {

        Type t= typeDao.getType(id);  //更新前先查询
        if(t==null){
            throw new NotFoundException("更新失败，没有找到该分类");
        }
        return typeDao.updateType(type.getName(), id);
    }
    @Transactional
    @Override
    public int deleteType(Integer id) {
        typeDao.deleteType(id);
        return 0;
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        return typeDao.listTypeTop(size);
    }
}
