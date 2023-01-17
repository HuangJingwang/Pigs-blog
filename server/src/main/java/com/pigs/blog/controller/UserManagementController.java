package com.pigs.blog.controller;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.LoginRequest;
import com.pigs.blog.model.User;
import com.pigs.blog.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "pigs-blog/user", tags = "用户接口")
@RestController
@RequestMapping("pigs-blog/user")
public class UserManagementController {
    @Autowired
    private LoginService loginService;
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "LoginRequest", name = "loginRequest", value = "", required = true)
    })
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse login(@RequestBody LoginRequest loginRequest){
        User user = new User();
        BeanUtils.copyProperties(loginRequest, user);
        ResultResponse login = loginService.login(user);
        return login;
    }

    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse logout(){
        ResultResponse logout = loginService.logout();
        return logout;
    }
}
