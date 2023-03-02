package com.pigs.blog.controller;

import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.HomepageUserInfoRequest;
import com.pigs.blog.contract.request.UserInfoPageDataRequest;
import com.pigs.blog.contract.response.HomepageUserInfoResponse;
import com.pigs.blog.contract.response.UserInfoPageDataResponse;
import com.pigs.blog.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "pigs-blog/user-info", tags = "用户信息接口")
@RestController
@RequestMapping("pigs-blog/user-info")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNo", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "articlesCountMoreThan", value = "")
    })
    @ApiOperation(value = "筛选用户的用户信息", notes = "", httpMethod = "GET")
    @RequestMapping(value = "getPageData", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<PageData<UserInfoPageDataResponse>> getPageData(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "articlesCountMoreThan", required = false, defaultValue = "-1") Integer articlesCountMoreThan
    ){
        UserInfoPageDataRequest request = new UserInfoPageDataRequest();
        request.setArticlesCountMoreThan(articlesCountMoreThan);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        PageData<UserInfoPageDataResponse> result =  userInfoService.getPageData(request);
        return ResultResponse.success(result);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "account", value = "", required = true)
    })
    @ApiOperation(value = "获取首页用户信息", notes = "", httpMethod = "GET")
    @RequestMapping(value = "getHomepageUserInfo", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<HomepageUserInfoResponse> getHomepageUserInfo(
            @RequestParam(name = "account", required = false) String account
    ){
        HomepageUserInfoRequest request = new HomepageUserInfoRequest();
        request.setAccount(account);
        HomepageUserInfoResponse response = userInfoService.getHomepageUserInfo(request);

        return ResultResponse.success(response);
    }

//    @RequestMapping(value = "getUserList", method = RequestMethod.GET, produces = "application/json")
//    public ResultResponse<HomepageUserInfoResponse> getUserList(){
//
//        return ResultResponse.success();
//    }
//
//    @RequestMapping(value = "getUserDetail", method = RequestMethod.GET, produces = "application/json")
//    public ResultResponse<HomepageUserInfoResponse> getUserDetail(){
////        文章数。。
//        return ResultResponse.success();
//    }
}
