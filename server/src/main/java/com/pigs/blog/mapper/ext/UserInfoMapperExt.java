package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.User;
import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.criteria.UserInfoPageCriteria;
import com.pigs.blog.model.vo.HomepageUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoMapperExt {
    Long countPageByCriteria(@Param("criteria") UserInfoPageCriteria criteria);

    List<UserInfo> selectPageByCriteria(@Param("criteria") UserInfoPageCriteria criteria);

    UserInfo selectUserInfoByAccount(@Param("account") String account);

    HomepageUserInfo selectHomepageUserInfoByAccount(@Param("account") String account);
}
