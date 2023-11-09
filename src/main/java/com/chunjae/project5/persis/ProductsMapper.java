package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Category;
import com.chunjae.project5.entity.PhotosVO;
import com.chunjae.project5.entity.Products;
import com.chunjae.project5.entity.ProductsVO;
import com.chunjae.project5.util.BoardPage;
import com.chunjae.project5.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductsMapper {

    List<ProductsVO> getList(Page page);
    List<Category> categories ();
    int getCount(Page page);
    List<PhotosVO> photosList (@Param("productNo") int productNO);
    void productsInsert(Products products);
}
