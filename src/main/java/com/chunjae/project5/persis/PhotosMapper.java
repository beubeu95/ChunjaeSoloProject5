package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Market;
import com.chunjae.project5.entity.Photos;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PhotosMapper {
    public List<Photos> photosList(int marketNo) throws Exception;
    public void photosInsert(Market market) throws Exception;
    public void photosEdit(Market market) throws Exception;
    public void photosDelete(int marketNo) throws Exception;

}
