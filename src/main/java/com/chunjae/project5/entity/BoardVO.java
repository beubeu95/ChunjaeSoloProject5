package com.chunjae.project5.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private int seq;
    private String cateName;
    private String cate;
    private String title;
    private String content;
    private int nickname;
    private Date regdate;
    private int visited;
}
