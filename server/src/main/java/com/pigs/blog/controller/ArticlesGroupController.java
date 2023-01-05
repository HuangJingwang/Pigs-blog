package com.pigs.blog.controller;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesGroupListRequest;
import com.pigs.blog.contract.request.ArticlesGroupSaveRequest;
import com.pigs.blog.contract.response.ArticlesGroupListResponse;
import com.pigs.blog.model.ArticlesGroup;
import com.pigs.blog.service.ArticlesGroupInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "pigs-blog/articles",tags = "文章分类接口")
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
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "parentId", value = "", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "ArticlesGroupSaveRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "保存或者编辑", notes = "", httpMethod = "POST")
    @RequestMapping(value = "save/{parentId}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse saveArticlesGroup(@PathVariable("parentId") Integer parentId,
                                            @RequestBody @Valid ArticlesGroupSaveRequest request){
        request.setParentId(parentId);
        groupInterface.save(request);
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
