package com.pigs.blog.controller;

import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesPageDataRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.contract.response.ArticlesPreOrNextResponse;
import com.pigs.blog.service.ArticlesInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pigs-blog/articles", tags = "文章接口")
@RestController
@RequestMapping("pigs-blog/articles")
public class ArticlesController {
    @Autowired
    private ArticlesInterface articlesInterface;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNo", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "account", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "status", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "boolean", name = "orderByPV", value = "")
    })
    @ApiOperation(value = "分页接口", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/getArticlesPageData", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<PageData<ArticlesListResponse>> getArticlesPageData(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(name = "account", required = false, defaultValue = "") String account,
            @RequestParam(name = "status", required = false, defaultValue = "") String status,
            @RequestParam(name = "orderByPV", required = false, defaultValue = "false") Boolean orderByPV) {
        ArticlesPageDataRequest request = new ArticlesPageDataRequest();
        request.setAccount(account);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        request.setStatus(status);
        request.setOrderByPV(orderByPV);
        PageData<ArticlesListResponse> pageData = articlesInterface.getPageData(request);
        return ResultResponse.success(pageData);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesCreateRequest", name = "request", value = "", required = true)
    })
    @PreAuthorize("hasAuthority('root')")
    @ApiOperation(value = "保存", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse saveArticles(@RequestBody @Valid ArticlesCreateRequest request) {
        return articlesInterface.saveArticles(request);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesUpdateRequest", name = "request", value = "", required = true)
    })
    @PreAuthorize("hasAuthority('root')")
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse updateArticles(@PathVariable("id") Long id,
                                         @RequestBody @Valid ArticlesUpdateRequest request) {
        articlesInterface.updateArticles(id, request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true)
    })
    @PreAuthorize("hasAuthority('root')")
    @ApiOperation(value = "物理删除", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/delete-forever/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deleteForever(@PathVariable Long id) {
        articlesInterface.deleteForever(id);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true)
    })
    @PreAuthorize("hasAuthority('root')")
    @ApiOperation(value = "逻辑删除", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deleteArticles(@PathVariable Long id) {
        articlesInterface.deleteArticles(id);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "文章详情", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<ArticlesDetailResponse> detail(@PathVariable Long id) {
        ArticlesDetailResponse response = articlesInterface.getDetailArticles(id);
        return ResultResponse.success(response);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "groupId", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "tags", value = "")
    })
    @ApiOperation(value = "文章列表", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/listArticles", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<List<ArticlesListResponse>> listArticlesByGroupId(
            @RequestParam(value = "groupId", required = false) Integer groupId,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "status", required = false) String status) {
        ArticlesListRequest request = new ArticlesListRequest();
        request.setStatus(status);
        request.setTags(tags);
        request.setGroupId(groupId);
        request.setAccount(account);
        List<ArticlesListResponse> list = articlesInterface.listArticlesByCriteria(request);
        return ResultResponse.success(list);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "上一篇文章", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/getPreArticle/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<ArticlesPreOrNextResponse> getPreArticle(@PathVariable("id") Long id) {
        ArticlesPreOrNextResponse result = articlesInterface.findPreArticle(id);
        return ResultResponse.success(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "下一篇文章", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/getNextArticle/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<ArticlesPreOrNextResponse> getNextArticle(@PathVariable("id") Long id) {
        ArticlesPreOrNextResponse result = articlesInterface.findNextArticle(id);
        return ResultResponse.success(result);
    }
}
