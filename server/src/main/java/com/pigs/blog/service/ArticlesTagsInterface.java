package com.pigs.blog.service;

import com.pigs.blog.contract.request.ArticlesTagsCreateRequest;
import com.pigs.blog.contract.request.ArticlesTagsUpdateRequest;
import com.pigs.blog.contract.response.ArticlesTagsListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesTagsInterface {
    List<ArticlesTagsListResponse> list();

    void create(ArticlesTagsCreateRequest request);

    void update(Integer id, ArticlesTagsUpdateRequest request);

    void delete(Integer id);
}
