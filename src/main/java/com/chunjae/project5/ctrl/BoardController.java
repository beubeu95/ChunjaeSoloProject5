package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.BoardService;
import com.chunjae.project5.entity.Board;
import com.chunjae.project5.entity.BoardCate;
import com.chunjae.project5.entity.BoardVO;
import com.chunjae.project5.entity.Notice;
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

        String category = request.getParameter("cate");

        List<BoardVO> list = boardService.getList();
        List<BoardCate> categories = boardService.categories();
        model.addAttribute("list", list);
        model.addAttribute("categories", categories);
        model.addAttribute("curCategory", category);

        return "board/boardList";
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
