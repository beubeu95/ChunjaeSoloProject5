package com.chunjae.project5.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Products {
    private int productNO;
    private String title;
    private int price;
    private String description;
    private int userId;
    private String active;
    private String condition;
    private Date regdate;
    private String location;
    private String category;
}
