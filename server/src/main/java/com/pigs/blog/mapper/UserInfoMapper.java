package com.pigs.blog.mapper;

import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.UserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfo row);

    int insertSelective(UserInfo row);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserInfo row, @Param("example") UserInfoExample example);

    int updateByExample(@Param("row") UserInfo row, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo row);

    int updateByPrimaryKey(UserInfo row);
}