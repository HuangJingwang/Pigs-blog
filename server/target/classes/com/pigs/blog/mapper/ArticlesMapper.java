package com.pigs.blog.mapper;

import com.pigs.blog.model.Articles;
import com.pigs.blog.model.ArticlesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Articles row);

    int insertSelective(Articles row);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExample(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles row);

    int updateByPrimaryKey(Articles row);
}