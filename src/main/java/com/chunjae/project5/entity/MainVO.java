package com.chunjae.project5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainVO {
    private int marketNo;
    private String title;
    private int readable;
    private int price;
    private String content;
    private String loginId;
    private String active;
    private String conditions;
    private String regdate;
    private String selectedAddress;
    private String detailAddress;
    private double xdata;
    private double ydata;
    private int mainphoto_no;
    private String saveFolder;
    private String originFile;
    private String saveFile;
}
