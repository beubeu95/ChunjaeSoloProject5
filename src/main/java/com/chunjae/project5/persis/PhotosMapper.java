package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.Photos;
import com.chunjae.project5.entity.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PhotosMapper {
    void photosInsert(Products products);
    List<Photos> photosList (int productNO);
    public void photosDelete(@Param("productNo") int productNo);

}
