package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Market;
import com.chunjae.project5.entity.Photos;
import com.chunjae.project5.persis.PhotosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotosService {
    @Autowired
    private PhotosMapper photosMapper;

    public List<Photos> photosList(int marketNo) throws Exception{
      return  photosMapper.photosList(marketNo);
    }

    public void photosEdit(Market market) throws Exception{
        photosMapper.photosEdit(market);
    }
}
