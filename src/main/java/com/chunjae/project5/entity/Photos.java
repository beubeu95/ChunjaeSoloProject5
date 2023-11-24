package com.chunjae.project5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photos {
    private int photo_no;
    private int marketNo;
    private String saveFolder;
    private String originFile;
    private String saveFile;
}
