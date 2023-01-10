package com.pigs.blog.controller;

import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
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

@Api(value = "pigs-blog/articles",tags = "文章接口")
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
    @ApiOperation(value = "列表", notes = "", httpMethod = "GET")
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
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesCreateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "保存", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces="application/json")
    public ResultResponse saveArticles(@RequestBody @Valid ArticlesCreateRequest request){
        articlesInterface.saveArticles(request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesUpdateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse updateArticles(@RequestBody @Valid ArticlesUpdateRequest request){
        articlesInterface.updateArticles(request);
        return ResultResponse.success(null);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deleteArticles(@PathVariable Integer id){
        articlesInterface.deleteArticles(id);
        return ResultResponse.success(null);
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<ArticlesDetailResponse> detail(@PathVariable Integer id){
        ArticlesDetailResponse response = articlesInterface.getDetailArticles(id);
        return ResultResponse.success(response);
    }
}
