package com.pigs.blog.controller;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesTagsCreateRequest;
import com.pigs.blog.contract.request.ArticlesTagsUpdateRequest;
import com.pigs.blog.contract.response.ArticlesTagsListResponse;
import com.pigs.blog.service.ArticlesTagsInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pigs-blog/articles-tags",tags = "标签接口")
@RestController
@RequestMapping("pigs-blog/articles-tags")
public class ArticlesTagsController {
    @Autowired
    private ArticlesTagsInterface tagsInterface;

    @ApiOperation(value = "标签列表", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<List<ArticlesTagsListResponse>> listArticlesTagsList(){
        List<ArticlesTagsListResponse> result = tagsInterface.list();
        return ResultResponse.success(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesTagsCreateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "创建标签", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse createArticlesTagsList(@RequestBody @Valid ArticlesTagsCreateRequest request){
        tagsInterface.create(request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesTagsUpdateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse updateArticlesTags(@PathVariable Integer id,
                                             @RequestBody @Valid ArticlesTagsUpdateRequest request){
        tagsInterface.update(id, request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deleteArticlesTags(@PathVariable Integer id){
        tagsInterface.delete(id);
        return ResultResponse.success(null);
    }
}



