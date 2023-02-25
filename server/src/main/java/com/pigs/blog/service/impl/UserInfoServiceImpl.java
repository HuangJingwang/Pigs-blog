package com.pigs.blog.service.impl;

import com.pigs.blog.common.ArticlesStatusEnum;
import com.pigs.blog.common.Constants;
import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.HomepageUserInfoRequest;
import com.pigs.blog.contract.request.UserInfoPageDataRequest;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.contract.response.HomepageUserInfoResponse;
import com.pigs.blog.contract.response.UserInfoPageDataResponse;
import com.pigs.blog.mapper.ArticlesMapper;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.mapper.ext.ArticlesMapperExt;
import com.pigs.blog.mapper.ext.UserInfoMapperExt;
import com.pigs.blog.model.Articles;
import com.pigs.blog.model.ArticlesExample;
import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.UserInfoExample;
import com.pigs.blog.model.criteria.UserInfoPageCriteria;
import com.pigs.blog.model.vo.HomepageUserInfo;
import com.pigs.blog.service.UserInfoService;
import com.pigs.blog.utils.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapperExt userInfoMapperExt;
    @Autowired
    private ArticlesMapper articlesMapper;
    @Autowired
    private ArticlesMapperExt articlesMapperExt;
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

    @Override
    public HomepageUserInfoResponse getHomepageUserInfo(HomepageUserInfoRequest request) {
        String account = request.getAccount();
        if(StringUtils.isEmpty(account)){
            return getWebsiteInfo();
        }
        HomepageUserInfo info = userInfoMapperExt.selectHomepageUserInfoByAccount(account);

        HomepageUserInfoResponse response = new HomepageUserInfoResponse();
        response.setAccount(account);
        response.setArticlesCount(info.getArticlesCount());
        response.setGithubUrl(info.getGithubUrl());
        response.setNickname(info.getNickname());
        response.setImgUrl(info.getImgUrl());
        response.setPageView(info.getPageView());

        return response;
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

    private HomepageUserInfoResponse getWebsiteInfo(){
        HomepageUserInfoResponse result = new HomepageUserInfoResponse();

        //查文章总数
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(ArticlesStatusEnum.PUBLISHED.getStatus());
        long count = articlesMapper.countByExample(example);
        result.setArticlesCount((int)count);

        //查询网站总访问量
        //每个人访问量相加
        Long pageViewCount = articlesMapperExt.selectAllPageViewCount();
        result.setPageView(pageViewCount);

        result.setGithubUrl(Constants.WEBSITE_GITHUB_URL);
        result.setNickname(Constants.WEBSITE_NICKNAME);
        result.setImgUrl(Constants.WEBSITE_IMG_URL);

        return result;
    }
}
