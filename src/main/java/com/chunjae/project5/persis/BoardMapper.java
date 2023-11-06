package com.chunjae.project5.persis;

import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.BoardCate;
import com.chunjae.project5.entity.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BoardMapper {
    List<BoardCate> categories ();
    List<BoardVO> getList();
    void boardInsert(@Param("param") Board param);

}
