package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Mainphoto;
import com.chunjae.project5.entity.Market;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MainphotoMapper {
    public List<Mainphoto> mainphotoList(int marketNo) throws Exception;
    public void mainphotoInsert(Market market) throws Exception;
    public void mainphotoDelete(int marketNo) throws Exception;
    public void mainphotoEdit(Market market) throws Exception;
}
