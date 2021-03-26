package com.wyx.blog.service;

import com.wyx.blog.domain.Type;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {
        //新增type
    int saveType(Type type);
        //查询type
    Type getType(Integer id);
        //分页
    List<Type> listPage();
        //修改type
    int updateType(Integer id,Type type);
     //删除type
    void deleteType(Integer id);
}
