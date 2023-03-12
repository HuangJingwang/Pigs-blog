package com.pigs.blog.service;

import com.pigs.blog.contract.request.AddPictureToWarehouseRequest;
import com.pigs.blog.contract.request.PicturesListRequest;
import com.pigs.blog.contract.response.PicturesListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {
    List<PicturesListResponse> listPictures(PicturesListRequest request);

    void addToPictureWareHouse(AddPictureToWarehouseRequest request);

    String getRandomPicture(String position);
}
