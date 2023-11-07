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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/board/update")
    public String boardUpdateForm(@RequestParam("seq")int seq, Model model){
        BoardVO detail = boardService.boardDetail(seq);
        List<BoardCate> categories = boardService.categories();
        model.addAttribute("detail", detail);
        model.addAttribute("categories", categories);
        return "board/boardUpdate";
    }

    @PostMapping("/board/update")
    public String boardUpdate(Board param){
        boardService.boardEdit(param);
        return "redirect:/board/list";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("seq")int seq, Model model){
        boardService.boardDelete(seq);
        return "redirect:/board/list";
    }

    @RequestMapping(value="imageUpload.do", method = RequestMethod.POST)
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {
        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try{
            //파일 이름 가져오기
            String fileName = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();

            //이미지 경로 생성
            String path = "C:\\hkdev\\proj\\sole\\chunjaeSoloProject5\\project5\\src\\main\\resources\\static\\upload\\board/";	// 이미지 경로 설정(폴더 자동 생성)
            //String path = request.getRealPath("/resource/uploadckImage/");
            String ckUploadPath = path + uid + "_" + fileName;
            File folder = new File(path);
            System.out.println("path:"+path);	// 이미지 저장경로 console에 확인
            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }

            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화

            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            String fileUrl = request.getContextPath() + "/board/ckImgSubmit.do?uid=" + uid + "&fileName=" + fileName; // 작성화면

            // 업로드시 메시지 출력
            printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
            printWriter.flush();

        }catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }
            } catch(IOException e) { e.printStackTrace(); }
        }
        return;
    }

    //ckeditor를 이용한 서버에 전송된 이미지 뿌려주기
    @RequestMapping(value="ckImgSubmit.do")
    public void ckSubmit(@RequestParam(value="uid") String uid, @RequestParam(value="fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //서버에 저장된 이미지 경로
        String path = "C:\\hkdev\\proj\\sole\\chunjaeSoloProject5\\project5\\src\\main\\resources\\static\\upload\\board/";	// 이미지 경로 설정(폴더 자동 생성)
        //String path = request.getRealPath("/resource/uploadckImage/");
        System.out.println("path:"+path);
        String sDirPath = path + uid + "_" + fileName;

        File imgFile = new File(sDirPath);

        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if(imgFile.isFile()){
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;

            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;

            try{
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();

                while((readByte = fileInputStream.read(buf)) != -1){
                    outputStream.write(buf, 0, readByte);
                }

                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();

            }catch(IOException e){
                e.printStackTrace();
            }finally {
                outputStream.close();
                fileInputStream.close();
                out.close();
            }
        }
    }
}
