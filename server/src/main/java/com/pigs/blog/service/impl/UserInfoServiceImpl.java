package com.pigs.blog.service.impl;

import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.UserInfoPageDataRequest;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.contract.response.UserInfoPageDataResponse;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.mapper.ext.UserInfoMapperExt;
import com.pigs.blog.model.Articles;
import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.criteria.UserInfoPageCriteria;
import com.pigs.blog.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoMapperExt userInfoMapperExt;

    @Override
    public PageData<UserInfoPageDataResponse> getPageData(UserInfoPageDataRequest request) {
        PageData<UserInfoPageDataResponse> result = new PageData<>();
        UserInfoPageCriteria criteria = createCriteria(request);
        Long count = userInfoMapperExt.countPageByCriteria(criteria);
        if (count == 0) {
            result.setHasNext(false);
            result.setResultList(Collections.emptyList());
            result.setTotalResult(0L);
            return result;
        }

        List<UserInfo> list = userInfoMapperExt.selectPageByCriteria(criteria);
        List<UserInfoPageDataResponse> resultList = copyList(list);
        result.setTotalResult(count);
        result.setResultList(resultList);
        result.setHasNext(((count + request.getPageSize() - 1) / request.getPageSize()) > request.getPageNo());
        return result;
    }

    private UserInfoPageCriteria createCriteria(UserInfoPageDataRequest request) {
        UserInfoPageCriteria criteria = new UserInfoPageCriteria();
        criteria.setArticlesCountMoreThan(request.getArticlesCountMoreThan());
        criteria.setOffset(request.getPageSize());
        criteria.setStart(request.getPageNo() - 1);
        return criteria;
    }

    private List<UserInfoPageDataResponse> copyList(List<UserInfo> list) {
        List<UserInfoPageDataResponse> responses = new ArrayList<>();
        list.forEach(s -> {
            UserInfoPageDataResponse response = new UserInfoPageDataResponse();
            BeanUtils.copyProperties(s, response);
            responses.add(response);
        });
        return responses;
    }
}
