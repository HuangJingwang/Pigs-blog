package com.pigs.blog.mapper;

import com.pigs.blog.model.Articles;
import com.pigs.blog.model.ArticlesExample;
import com.pigs.blog.model.ArticlesWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticlesWithBLOBs row);

    int insertSelective(ArticlesWithBLOBs row);

    List<ArticlesWithBLOBs> selectByExampleWithBLOBs(ArticlesExample example);

    List<Articles> selectByExample(ArticlesExample example);

    ArticlesWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ArticlesWithBLOBs row, @Param("example") ArticlesExample example);

    int updateByExampleWithBLOBs(@Param("row") ArticlesWithBLOBs row, @Param("example") ArticlesExample example);

    int updateByExample(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(ArticlesWithBLOBs row);

    int updateByPrimaryKeyWithBLOBs(ArticlesWithBLOBs row);

    int updateByPrimaryKey(Articles row);
}