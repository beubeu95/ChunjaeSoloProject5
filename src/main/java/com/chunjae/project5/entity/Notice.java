package com.chunjae.project5.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private int no;
    private String title;
    private String content;
    private int author;
    private Date regdate;
    private int cnt;
}
