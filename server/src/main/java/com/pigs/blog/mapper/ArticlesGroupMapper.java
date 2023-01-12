package com.pigs.blog.mapper;

import com.pigs.blog.model.ArticlesGroup;
import com.pigs.blog.model.ArticlesGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesGroupMapper {
    long countByExample(ArticlesGroupExample example);

    int deleteByExample(ArticlesGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticlesGroup row);

    int insertSelective(ArticlesGroup row);

    List<ArticlesGroup> selectByExample(ArticlesGroupExample example);

    ArticlesGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ArticlesGroup row, @Param("example") ArticlesGroupExample example);

    int updateByExample(@Param("row") ArticlesGroup row, @Param("example") ArticlesGroupExample example);

    int updateByPrimaryKeySelective(ArticlesGroup row);

    int updateByPrimaryKey(ArticlesGroup row);
}