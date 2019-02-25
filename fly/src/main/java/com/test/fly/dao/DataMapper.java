package com.test.fly.dao;

import com.test.fly.pojo.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {
    int deleteByPrimaryKey(Integer msid);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer msid);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);

    List<Data> selectLocate();

    List<Data> historyLocate();
}