package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.BoardService;
import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.BoardCate;
import com.chunjae.project5.entity.BoardVO;
import com.chunjae.project5.entity.Notice;
import com.chunjae.project5.util.BoardPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/list")
    public String getList(HttpServletRequest request, Model model){

        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String category = request.getParameter("cate");

        BoardPage page = new BoardPage();
        page.setCategory(category);                                // 카테고리 데이터 SET

        // 페이징에 필요한 데이터 저장
        int total = boardService.getCount(page);
        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<BoardVO> list = boardService.getList(page);
        List<BoardCate> categories = boardService.categories();

        model.addAttribute("list", list);
        model.addAttribute("categories", categories);
        model.addAttribute("curPage", curPage);
        model.addAttribute("curCategory", category);
        model.addAttribute("page", page);

        return "board/boardList";
    }

    @GetMapping("/board/detail")
    public String getBoard(@RequestParam("seq")int seq, Model model){
        BoardVO board = boardService.boardDetail(seq);
        model.addAttribute("board", board);

        return "board/boardDetail";
    }

    @GetMapping("/board/insert")
    public String boardInsertForm(Model model){
        List<BoardCate> categories = boardService.categories();
        model.addAttribute("categories", categories);
        return "board/boardInsert";
    }

    @PostMapping("/board/insert")
    public String boardInsert(Board param){
        boardService.boardInsert(param);
        return "redirect:/board/list";
    }


}
