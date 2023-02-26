package com.pigs.blog.service;

import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.LogionCreateRequest;
import com.pigs.blog.model.Logion;
import org.springframework.stereotype.Service;

/**
 * 获取箴言信息
 */
@Service
public interface LogionService {
    public Logion getLogion();

    PageData list(Integer pageSize, Integer pageNo);

    void delete(Integer id);

    void create(LogionCreateRequest request);
}
