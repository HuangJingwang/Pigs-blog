package com.pigs.blog.mapper;

import com.pigs.blog.model.Logion;
import com.pigs.blog.model.LogionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogionMapper {
    long countByExample(LogionExample example);

    int deleteByExample(LogionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logion row);

    int insertSelective(Logion row);

    List<Logion> selectByExample(LogionExample example);

    Logion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Logion row, @Param("example") LogionExample example);

    int updateByExample(@Param("row") Logion row, @Param("example") LogionExample example);

    int updateByPrimaryKeySelective(Logion row);

    int updateByPrimaryKey(Logion row);
}