package com.pigs.blog.mapper.ext;

import com.pigs.blog.model.Articles;
import com.pigs.blog.model.criteria.ArticlesListCriteria;
import com.pigs.blog.model.criteria.ArticlesPageCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticlesMapperExt {

    List<Articles> selectArticlesList(@Param("criteria") ArticlesPageCriteria criteria);

    Long countArticlesList(@Param("criteria") ArticlesPageCriteria criteria);

    List<Articles> selectArticlesListByCriteria(@Param("criteria") ArticlesListCriteria criteria);

    List<Articles> selectPreArticle(@Param("start") long start);

    List<Articles> selectNextArticle(@Param("start") long start);

    Long getMaxId();

    @Select("select sum(page_view) from articles where status = 'published'")
    Long selectAllPageViewCount();

    Long getPublishedPageView(@Param("account") String account);
}
