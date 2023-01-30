package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.criteria.UserInfoPageCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapperExt {
    Long countPageByCriteria(@Param("criteria") UserInfoPageCriteria criteria);

    List<UserInfo> selectPageByCriteria(@Param("criteria") UserInfoPageCriteria criteria);
}
