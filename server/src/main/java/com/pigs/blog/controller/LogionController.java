package com.pigs.blog.controller;

import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.LogionCreateRequest;
import com.pigs.blog.model.Logion;
import com.pigs.blog.service.LogionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "pigs-blog/logion", tags = "箴言管理")
@RestController
@RequestMapping("pigs-blog/logion")
public class LogionController {

    @Autowired
    LogionService logionService;

    @ApiOperation(value = "随机给一个", notes = "", httpMethod = "GET")
    @RequestMapping(value = "random", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse getLogion(){
        Logion logion = logionService.getLogion();
        return ResultResponse.success(logion);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = ""),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageNo", value = "")
    })
    @ApiOperation(value = "列表", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<PageData<Logion>> getList(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo
    ){
      PageData result =   logionService.list(pageSize, pageNo);
      return ResultResponse.success(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "int", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse delete(@PathVariable("id") Integer id){
        logionService.delete(id);
        return ResultResponse.success(null);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "LogionCreateRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "创建", notes = "", httpMethod = "POST")
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse create(@RequestBody @Valid LogionCreateRequest request){
        logionService.create(request);
        return ResultResponse.success(null);
    }
}
