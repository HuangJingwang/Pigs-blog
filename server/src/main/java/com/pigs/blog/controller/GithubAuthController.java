package com.pigs.blog.controller;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.service.GithubAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@Api(value = "github", tags = "github")
@RestController
@RequestMapping("/github")
public class GithubAuthController {
    /**
     * github认证的业务处理类
     */
    @Autowired
    private GithubAuthService githubAuthService;

    /**
     * 跳转到github的授权登录页面
     *
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "跳转到github的授权登录页面", notes = "跳转到github的授权登录页面", httpMethod = "GET")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public void showLoginPage(HttpServletResponse response, @RequestParam(name = "url", required = false) String url) throws Exception {
        String path = githubAuthService.getGithubAuthPath(url);
        response.sendRedirect(path);
    }

    /**
     * 在github页面登录成功后，github会回调这个
     *
     * @param code
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "", required = true)
    })
    @ApiOperation(value = "在github页面登录成功后，github会回调这个(前端不用管)", notes = "在github页面登录成功后，github会回调这个", httpMethod = "GET")
    @RequestMapping(value = "/callback", method = RequestMethod.GET, produces = "application/json")
    public void callback(@RequestParam(value = "code") String code, HttpServletResponse resp) {
        githubAuthService.callback(code, resp);
    }
}

