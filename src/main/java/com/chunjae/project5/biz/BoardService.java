package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.BoardCate;
import com.chunjae.project5.entity.BoardVO;
import com.chunjae.project5.persis.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<BoardCate> categories() { return boardMapper.categories(); }
    public List<BoardVO> getList() { return boardMapper.getList(); }
    public void boardInsert (Board param) { boardMapper.boardInsert(param); }


}
