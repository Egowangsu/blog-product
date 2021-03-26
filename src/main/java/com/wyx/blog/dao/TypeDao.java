package com.wyx.blog.dao;

import com.wyx.blog.domain.Type;

import java.util.List;

public interface TypeDao {
    int saveType(Type type);

    Type getType(Integer id);

    int updateType(Integer id, Type type);

    void deleteType(Integer id);

    List<Type> selectAllType();
}
