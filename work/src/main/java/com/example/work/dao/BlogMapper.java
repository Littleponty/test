package com.example.work.dao;

import com.example.work.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> query();
}