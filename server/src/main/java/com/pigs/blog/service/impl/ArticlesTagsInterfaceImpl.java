package com.pigs.blog.service.impl;

import com.pigs.blog.contract.request.ArticlesTagsCreateRequest;
import com.pigs.blog.contract.request.ArticlesTagsUpdateRequest;
import com.pigs.blog.contract.response.ArticlesTagsListResponse;
import com.pigs.blog.mapper.ArticlesTagsMapper;
import com.pigs.blog.model.ArticlesTags;
import com.pigs.blog.model.ArticlesTagsExample;
import com.pigs.blog.service.ArticlesTagsInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesTagsInterfaceImpl implements ArticlesTagsInterface {
    @Autowired
    private ArticlesTagsMapper tagsMapper;


    @Override
    public List<ArticlesTagsListResponse> list() {
        ArticlesTagsExample example = new ArticlesTagsExample();
        example.setOrderByClause("id desc");
        List<ArticlesTags> articlesTags = tagsMapper.selectByExample(example);
        List<ArticlesTagsListResponse> result = copyList(articlesTags);
        return result;
    }

    @Override
    public void create(ArticlesTagsCreateRequest request) {
        ArticlesTags articlesTags = new ArticlesTags();
        BeanUtils.copyProperties(request,articlesTags);
        tagsMapper.insertSelective(articlesTags);
    }

    @Override
    public void update(Long id, ArticlesTagsUpdateRequest request) {
        ArticlesTags articlesTags = new ArticlesTags();
        BeanUtils.copyProperties(articlesTags,request);
        articlesTags.setId(id);
        tagsMapper.updateByPrimaryKeySelective(articlesTags);
    }

    @Override
    public void delete(Long id) {
        tagsMapper.deleteByPrimaryKey(id);
    }


    private List<ArticlesTagsListResponse> copyList(List<ArticlesTags> list) {
        List<ArticlesTagsListResponse> responses = new ArrayList<>();
        list.forEach(s -> {
            ArticlesTagsListResponse response = new ArticlesTagsListResponse();
            BeanUtils.copyProperties(s, response);
            responses.add(response);
        });
        return responses;
    }
}
