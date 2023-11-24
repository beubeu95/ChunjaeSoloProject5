package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {

    List<Faq> getList();
}
