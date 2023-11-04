package com.chunjae.project5.entity;

import lombok.Data;

@Data
public class Notice {
    private int no;
    private String title;
    private String content;
    private int author;
    private String regdate;
    private int cnt;
}
