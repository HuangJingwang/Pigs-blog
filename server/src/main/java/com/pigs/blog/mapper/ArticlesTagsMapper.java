package com.pigs.blog.mapper;

import com.pigs.blog.model.ArticlesTags;
import com.pigs.blog.model.ArticlesTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticlesTagsMapper {
    long countByExample(ArticlesTagsExample example);

    int deleteByExample(ArticlesTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ArticlesTags row);

    int insertSelective(ArticlesTags row);

    List<ArticlesTags> selectByExample(ArticlesTagsExample example);

    ArticlesTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ArticlesTags row, @Param("example") ArticlesTagsExample example);

    int updateByExample(@Param("row") ArticlesTags row, @Param("example") ArticlesTagsExample example);

    int updateByPrimaryKeySelective(ArticlesTags row);

    int updateByPrimaryKey(ArticlesTags row);
}