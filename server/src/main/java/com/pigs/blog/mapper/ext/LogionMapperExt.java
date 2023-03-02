package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.Logion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogionMapperExt {

    List<Logion> listAll(@Param("start") Integer start, @Param("offset") Integer offset);

    Logion selectRandom();
}
