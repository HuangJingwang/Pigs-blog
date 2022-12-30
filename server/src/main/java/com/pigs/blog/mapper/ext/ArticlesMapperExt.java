package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.Articles;
import com.pigs.blog.model.criteria.ArticlesListCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ArticlesMapperExt {
    List<Articles> selectArticlesList(@Param("criteria") ArticlesListCriteria criteria);
    Long countArticlesList(@Param("criteria") ArticlesListCriteria criteria);
}
