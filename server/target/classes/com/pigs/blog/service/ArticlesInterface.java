package com.pigs.blog.service;

import com.pigs.blog.common.PageData;

import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.response.ArticlesListResponse;
import org.springframework.stereotype.Service;

@Service
public interface ArticlesInterface {
    PageData<ArticlesListResponse> getPageData(ArticlesListRequest request);
}
