package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.ProductsService;
import com.chunjae.project5.entity.Category;
import com.chunjae.project5.entity.Photos;
import com.chunjae.project5.entity.Products;
import com.chunjae.project5.entity.ProductsVO;
import com.chunjae.project5.util.BoardPage;
import com.chunjae.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/list")
    public String getList(HttpServletRequest request, Model model){

        //Page
        int curPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        String category = request.getParameter("category");

        BoardPage page = new BoardPage();
        page.setKeyword(request.getParameter("keyword"));
        page.setType(request.getParameter("type"));
        page.setCategory(String.valueOf(category));

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

    @GetMapping("/products/insert")
    public String productsInsertForm(Model model){

        List<Category> categories = productsService.categories();
        model.addAttribute("categories", categories);

        return "products/productsInsert";
    }

    @RequestMapping(value = "/products/insert", method = RequestMethod.POST)
    public String write(Products products, @RequestParam("upfile") MultipartFile[] files, HttpServletRequest req, Model model, RedirectAttributes rttr) throws Exception {


            String realPath = req.getRealPath("/resources/static/upload/products/");           // 업로드 경로 설정
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveFolder = realPath + today;
            File folder = new File(saveFolder);
            if(!folder.exists()) {                                  // 폴더가 존재하지 않으면 폴더 생성
                folder.mkdirs();
            }
            List<Photos> photosList = new ArrayList<>();        // 첨부파일 정보를 리스트로 생성
            for(MultipartFile file : files) {
                Photos photos = new Photos();
                String originalFileName = file.getOriginalFilename(); // 첨부파일의 실제 파일명
                if(!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));     // 파일 이름을 랜덤으로 설정
                    photos.setSaveFoler(today);
                    photos.setRealname(originalFileName);
                    photos.setPhotoFile(saveFileName);
                    file.transferTo(new File(folder, saveFileName));    // 파일을 업로드 폴더에 저장
                }
                photosList.add(photos);
            }
            products.setPhotosList(photosList);


            try {
                productsService.productsInsert(products);
                rttr.addFlashAttribute("msg", "자료실에 글을 등록하였습니다");
            } catch(Exception e) {
                e.printStackTrace();
                rttr.addFlashAttribute("msg", "글 작성 중 문제가 발생했습니다");
            }

        return "redirect:/products/list";
    }

    

}
