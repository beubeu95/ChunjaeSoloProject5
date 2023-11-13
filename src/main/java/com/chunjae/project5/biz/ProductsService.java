package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Category;
import com.chunjae.project5.entity.Products;
import com.chunjae.project5.entity.ProductsVO;
import com.chunjae.project5.persis.PhotosMapper;
import com.chunjae.project5.persis.ProductsMapper;
import com.chunjae.project5.util.BoardPage;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private PhotosMapper photosMapper;

    public List<ProductsVO> getList(Page page) { return productsMapper.getList(page); }
    public List<Category> categories() {return productsMapper.categories(); }
    public int getCount(Page page) { return productsMapper.getCount(page); }
    @Transactional
    public void productsInsert(Products products) {
        productsMapper.productsInsert(products);
        photosMapper.photosInsert(products);
    }

}
