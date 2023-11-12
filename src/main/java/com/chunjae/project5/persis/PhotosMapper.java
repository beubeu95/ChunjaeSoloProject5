package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Photos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PhotosMapper {
    void photosInsert(@Param("param") Photos param);
    List<Photos> photosList (@Param("productNo") int productNO);
    public void photosDelete(@Param("productNo") int productNo);

}
