package com.wyx.blog.service;

import com.wyx.blog.domain.Type;

import java.util.List;

public interface TypeService {
        //新增type
    int saveType(Type type);
        //查询type
    Type getType(Integer id);
        //分页
    List<Type> listPage();
        //修改type
    int updateType(Type type,Integer id);
     //删除type
    int deleteType(Integer id);

    Type getTypeByName(String name);
}
