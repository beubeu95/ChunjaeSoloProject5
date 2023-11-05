package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.NoticeService;
import com.chunjae.project5.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice/list")
    public String getList(Model model) {

        List<Notice> noticeList = noticeService.getList();
        model.addAttribute("noticeList", noticeList);

        return "notice/noticeList";
    }

    @GetMapping("/notice/detail")
    public String getNotice(HttpServletRequest request, Model model) {

        int no = Integer.parseInt(request.getParameter("no"));

        Notice notice = noticeService.getNotice(no);
        model.addAttribute("notice", notice);

        return "notice/noticeDetail";
    }

    @GetMapping("/notice/insert")
    public String noticeInsertForm(Model model){
        return "notice/noticeInsert";
    }

    @PostMapping("/notice/insert")
    public String noticeInsert(Notice param){
        noticeService.noticeInsert(param);
        return "redirect:/notice/list";
    }

    @GetMapping("/notice/update")
    public String noticeUpdateForm(@RequestParam("no") int no, Model model){
        Notice notice = noticeService.getNotice(no);
        model.addAttribute("notice", notice);
        return "/notice/noticeUpdate";
    }

    @PostMapping("/notice/update")
    public String noticeUpdate(Notice param, Model model){
        noticeService.noticeUpdate(param);
        return "redirect:/notice/list";
    }

    @GetMapping("/notice/delete")
    public String noticeDelete(@RequestParam("no") int no, Model model){
        noticeService.noticeDelete(no);
        return "redirect:/notice/list";
    }

}
