package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.FaqService;
import com.chunjae.project5.entity.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FaqCtrl {

    @Autowired
    private FaqService faqService;

    @GetMapping("/faq/list")
    public String faqList(Model model) {

        List<Faq> list= faqService.getList();
        model.addAttribute("list", list);
        return "faq/faqList";
    }
}
