package com.pigs.blog.controller;

import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesSaveRequest;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.service.ArticlesInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("pigs-blog/articles")
@RestController
@RequestMapping("pigs-blog/articles")
public class ArticlesController {
    @Autowired
    private ArticlesInterface articlesInterface;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNo", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "author", value = "")
    })
    @ApiOperation(value = "文章列表", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/getArticles", method = RequestMethod.GET, produces="application/json")
    public ResultResponse<PageData<ArticlesListResponse>> getArticlesPageData(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "author", required = false, defaultValue = "") String author) {
        ArticlesListRequest request = new ArticlesListRequest();
        request.setAuthor(author);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        PageData<ArticlesListResponse> pageData = articlesInterface.getPageData(request);
        return ResultResponse.success(pageData);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesSaveRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "文章保存", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces="application/json")
    public ResultResponse saveArticles(@RequestBody @Valid ArticlesSaveRequest request){
        articlesInterface.saveArticles(request);
        return ResultResponse.success(null);
    }
}
