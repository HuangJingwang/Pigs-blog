package com.pigs.blog.service;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesGroupCreateRequest;
import com.pigs.blog.contract.request.ArticlesGroupListRequest;
import com.pigs.blog.contract.request.ArticlesGroupUpdateRequest;
import com.pigs.blog.contract.response.ArticlesGroupListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesGroupInterface {
    List<ArticlesGroupListResponse> list(ArticlesGroupListRequest request);

    ResultResponse create(ArticlesGroupCreateRequest request);

    void deleteById(Long id);

    ResultResponse update(ArticlesGroupUpdateRequest request, Long id);
}
