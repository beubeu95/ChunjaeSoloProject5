package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Mainphoto;
import com.chunjae.project5.persis.MainphotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class MainPhotoService {
    @Autowired
    private MainphotoMapper mainphotoMapper;
    public List<Mainphoto> mainphotoList(int marketNo) throws Exception{
        return mainphotoMapper.mainphotoList(marketNo);
    }
}
