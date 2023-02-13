package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapperExt {
    User selectUserByAccount(@Param("account") String account);
}
