package com.chunjae.project5.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Products {
    private int productNo;
    private String title;
    private int price;
    private String description;
    private int userId;
    private String active;
    private String condition2;
    private Date regdate;
    private String addr1;
    private String addr2;
    private String postcode;
    private String category;
    private List<Photos> photosList;
}
