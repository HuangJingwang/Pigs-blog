package com.pigs.blog.service;

import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.HomepageUserInfoRequest;
import com.pigs.blog.contract.request.UserInfoPageDataRequest;
import com.pigs.blog.contract.response.HomepageUserInfoResponse;
import com.pigs.blog.contract.response.UserInfoPageDataResponse;
import com.pigs.blog.model.vo.HomepageUserInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
    PageData<UserInfoPageDataResponse> getPageData(UserInfoPageDataRequest request);

    HomepageUserInfoResponse getHomepageUserInfo(HomepageUserInfoRequest request);
}
