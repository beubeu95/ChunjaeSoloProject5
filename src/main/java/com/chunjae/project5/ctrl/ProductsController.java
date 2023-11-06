package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.ProductsService;
import com.chunjae.project5.entity.Category;
import com.chunjae.project5.entity.ProductsVO;
import com.chunjae.project5.util.BoardPage;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/list")
    public String getList(HttpServletRequest request, Model model){

        //Page
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String category = request.getParameter("cate");

        BoardPage page = new BoardPage();
        page.setKeyword(request.getParameter("keyword"));
        page.setType(request.getParameter("type"));
        page.setCategory(category);

        //페이징에 필요한 데이터 저장
        int total = productsService.getCount(page);
        page.makeBlock(curPage, total);
        page.makeLastPageNum(total);
        page.makePostStart(curPage, total);

        List<ProductsVO> list = productsService.getList(page);
        List<Category> categories = productsService.categories();

        model.addAttribute("list", list);          // 게시글 목록
        model.addAttribute("categories", categories);       // 카테고리 목록
        model.addAttribute("curPage", curPage);             // 현재 페이지
        model.addAttribute("curCategory", category);        // 현재 카테고리
        model.addAttribute("page", page);                   // 페이징 데이터

        return "products/productsList";
    }
}
