package com.chunjae.project5.biz;

import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.BoardCate;
import com.chunjae.project5.entity.BoardVO;
import com.chunjae.project5.persis.BoardMapper;
import com.chunjae.project5.persis.PhotosMapper;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;



    public List<BoardCate> categories() { return boardMapper.categories(); }
    public List<BoardVO> getList(Page page) { return boardMapper.getList(page); }
    public int getCount(Page page) { return boardMapper.getCount(page); }
    public BoardVO boardDetail(int seq) { return boardMapper.boardDetail(seq);}

    public void visitedCount(int seq) { boardMapper.visitedCount(seq); }

    public void boardInsert (Board param) {boardMapper.boardInsert(param);}
    public void boardEdit (Board param) { boardMapper.boardEdit(param); }
    public void boardDelete (int seq) { boardMapper.boardDelete(seq); }

}
