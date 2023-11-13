package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Photos;
import com.chunjae.project5.entity.Products;
import com.chunjae.project5.persis.PhotosMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotosService {

    @Autowired
    private PhotosMapper photosMapper;

    public void photosInsert(Products products) { photosMapper.photosInsert(products); }
    public List<Photos> photosList (@Param("productNo") int productNO){ return photosMapper.photosList(productNO); }
    public void photosDelete(@Param("productNo") int productNo){ photosMapper.photosDelete(productNo); }
}
