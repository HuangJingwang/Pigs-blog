package com.pigs.blog.mapper;

import com.pigs.blog.model.Articles;
import com.pigs.blog.model.ArticlesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Articles row);

    int insertSelective(Articles row);

    List<Articles> selectByExampleWithBLOBs(ArticlesExample example);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExampleWithBLOBs(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExample(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles row);

    int updateByPrimaryKeyWithBLOBs(Articles row);

    int updateByPrimaryKey(Articles row);
}