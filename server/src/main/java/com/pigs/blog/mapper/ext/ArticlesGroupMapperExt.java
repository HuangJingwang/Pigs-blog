package com.pigs.blog.mapper.ext;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ArticlesGroupMapperExt {
    List<String> selectGroupNameById(@Param("groupIds") List<Long> groupIds);
}
