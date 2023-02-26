package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.Logion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogionMapperExt {
    @Select("SELECT id FROM `logion`")
    List<Integer> selectAllIds();

    List<Logion> listAll(@Param("start") Integer start, @Param("offset") Integer offset);
}
