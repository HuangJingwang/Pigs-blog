package com.pigs.blog.service.impl;

import com.pigs.blog.contract.request.AddPictureToWarehouseRequest;
import com.pigs.blog.contract.request.PicturesListRequest;
import com.pigs.blog.contract.response.PicturesListResponse;
import com.pigs.blog.mapper.PicturesMapper;
import com.pigs.blog.model.Pictures;
import com.pigs.blog.model.PicturesExample;
import com.pigs.blog.service.PictureService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PicturesMapper warehouseMapper;
    @Override
    public List<PicturesListResponse> listPictures(PicturesListRequest request) {
        PicturesExample example = new PicturesExample();
        PicturesExample.Criteria criteria = example.createCriteria();
        if(Strings.isNotBlank(request.getPosition())){
            criteria.andPositionEqualTo(request.getPosition());
        }
        if(Strings.isNotBlank(request.getUploadBy())){
            criteria.andUploadByEqualTo(request.getUploadBy());
        }
        List<Pictures> Picturess = warehouseMapper.selectByExample(example);
        return copyList(Picturess);
    }

    @Override
    public void addToPictureWareHouse(AddPictureToWarehouseRequest request) {
        Pictures pictures = new Pictures();
        BeanUtils.copyProperties(request,pictures);
        warehouseMapper.insertSelective(pictures);
    }

    private List<PicturesListResponse> copyList(List<Pictures> list) {
        List<PicturesListResponse> responses = new ArrayList<>();
        list.forEach(s -> {
            PicturesListResponse response = new PicturesListResponse();
            BeanUtils.copyProperties(s, response);
            responses.add(response);
        });
        return responses;
    }
}
