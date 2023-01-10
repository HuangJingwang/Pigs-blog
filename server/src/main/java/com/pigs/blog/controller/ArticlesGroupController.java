package com.pigs.blog.controller;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesGroupCreateRequest;
import com.pigs.blog.contract.request.ArticlesGroupListRequest;
import com.pigs.blog.contract.request.ArticlesGroupUpdateRequest;
import com.pigs.blog.contract.response.ArticlesGroupListResponse;
import com.pigs.blog.service.ArticlesGroupInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pigs-blog/articles-group", tags = "文章分类接口")
@RestController
@RequestMapping("pigs-blog/articles-group")
public class ArticlesGroupController {
    @Autowired
    private ArticlesGroupInterface groupInterface;
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "author", value = "")
    })
    @ApiOperation(value = "分类列表", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<List<ArticlesGroupListResponse>> getArticlesGroupList(
            @RequestParam(value = "author", required = false) String author){
        ArticlesGroupListRequest request = new ArticlesGroupListRequest();
        request.setAuthor(author);
        List<ArticlesGroupListResponse> result = groupInterface.list(request);
        return ResultResponse.success(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesGroupCreateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "创建", notes = "", httpMethod = "POST")
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse createArticlesGroup(@RequestBody @Valid ArticlesGroupCreateRequest request){
        groupInterface.create(request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesGroupUpdateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "编辑", notes = "", httpMethod = "POST")
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse updateArticlesGroup(@RequestBody @Valid ArticlesGroupUpdateRequest request){
        groupInterface.update(request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "逻辑删除", notes = "", httpMethod = "POST")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deleteArticlesGroup(@PathVariable("id") Integer id){
        groupInterface.deleteById(id);
        return ResultResponse.success(null);
    }
}
