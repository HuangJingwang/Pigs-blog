package com.pigs.blog.service;

import com.pigs.blog.common.PageData;

import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesListPageRequest;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
import com.pigs.blog.contract.response.ArticlesListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesInterface {
    PageData<ArticlesListResponse> getPageData(ArticlesListPageRequest request);

    void saveArticles(ArticlesCreateRequest request);

    void updateArticles(Long id, ArticlesUpdateRequest request);

    void deleteArticles(Long id);

    ArticlesDetailResponse getDetailArticles(Long id);

    List<ArticlesListResponse> listArticlesByGroupId(Long groupId);

    List<ArticlesListResponse> listArticlesByCriteria(ArticlesListRequest request);
}
