package com.chunjae.project5.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductsVO {
    private int productNo;
    private String title;
    private int price;
    private int userId;
    private String active;
    private String condition;
    private Date regdate;
    private String location;
    private int categoryNo;
}
