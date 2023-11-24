package com.chunjae.project5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chatting {
    private Long roomId;
    private int productNo;
    private String buyerId;
    private String regDate;
}
