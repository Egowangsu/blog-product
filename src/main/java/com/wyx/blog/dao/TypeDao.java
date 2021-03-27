package com.wyx.blog.dao;

import com.wyx.blog.domain.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeDao {
    int saveType(Type type);

    Type getType(Integer id);

    int updateType(@Param("name") String name,@Param("id") Integer id);

    void deleteType(Integer id);

    List<Type> selectAllType();

    Type getTypeByName(String name);
}
