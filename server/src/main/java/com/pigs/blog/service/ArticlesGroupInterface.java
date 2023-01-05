package com.pigs.blog.service;

import com.pigs.blog.contract.request.ArticlesGroupListRequest;
import com.pigs.blog.contract.request.ArticlesGroupSaveRequest;
import com.pigs.blog.contract.response.ArticlesGroupListResponse;
import com.pigs.blog.model.ArticlesGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesGroupInterface {
    List<ArticlesGroupListResponse> list(ArticlesGroupListRequest request);

    void save(ArticlesGroupSaveRequest request);

    void deleteById(Integer id);
}
