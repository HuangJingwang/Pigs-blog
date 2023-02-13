package com.pigs.blog.controller;

import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.AddPictureToWarehouseRequest;
import com.pigs.blog.contract.request.PictureDeleteRequest;
import com.pigs.blog.contract.request.PicturesListRequest;
import com.pigs.blog.contract.response.PicturesListResponse;
import com.pigs.blog.service.PictureService;
import com.pigs.blog.service.impl.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(value = "pigs-blog/picture", tags = "图片接口")
@RestController
@RequestMapping("pigs-blog/picture")
public class PictureController {
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private PictureService pictureService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "file", name = "file", value = "", required = true)
    })
    @ApiOperation(value = "上传图片到云", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse<String> uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = qiniuService.saveImage(file);
            return ResultResponse.success(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultResponse.fail(10003, "upload picture fail");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "PictureDeleteRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "删除云上的图片", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse deletePicture(@RequestBody PictureDeleteRequest request){
        qiniuService.deleteImage(request);
        return ResultResponse.success(null);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "position", value = "", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "uploadBy", value = "", required = true)
    })
    @ApiOperation(value = "列出图库图片", notes = "", httpMethod = "GET")
    @RequestMapping(value = "/listPictureWarehouse", method = RequestMethod.GET, produces = "application/json")
    public ResultResponse<List<PicturesListResponse>> listPictureWarehouse(@RequestParam("position") String position,
                                                                           @RequestParam("upload_by") String uploadBy){
        PicturesListRequest request = new PicturesListRequest();
        request.setPosition(position);
        request.setUploadBy(uploadBy);
        List<PicturesListResponse> response = pictureService.listPictures(request);
        return ResultResponse.success(response);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "AddPictureToWarehouseRequest", name = "request", value = "", required = true)
    })
    @ApiOperation(value = "添加图片到图库", notes = "", httpMethod = "POST")
    @RequestMapping(value = "/addToPictureWareHouse", method = RequestMethod.POST, produces = "application/json")
    public ResultResponse add(@RequestBody @Valid AddPictureToWarehouseRequest request){
        pictureService.addToPictureWareHouse(request);
        return ResultResponse.success(null);
    }
}
