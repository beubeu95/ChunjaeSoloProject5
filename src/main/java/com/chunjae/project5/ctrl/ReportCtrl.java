package com.chunjae.project5.ctrl;


import com.chunjae.project5.biz.ReportService;
import com.chunjae.project5.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ReportCtrl {

    @Autowired
    private ReportService reportService;


    

    @RequestMapping(value = "/report/repCheck", method = RequestMethod.POST)
    @ResponseBody
    public boolean repCheck(HttpServletRequest request, Principal principal) throws Exception {

        String sid = principal != null ? principal.getName() : "";
        int reqNo = Integer.parseInt(request.getParameter("reqNo"));

        int chk = reportService.reportchkReq(reqNo, sid);

        boolean result;
        if(chk == 0) {
            result = true;

        } else {
            result= false;
        }

        return result;
    }

    @RequestMapping(value = "/report/repCheckMar", method = RequestMethod.POST)
    @ResponseBody
    public boolean repCheckMar(HttpServletRequest request, Principal principal) throws Exception {

        String sid = principal != null ? principal.getName() : "";
        int ProductsNo = Integer.parseInt(request.getParameter("ProductsNo"));

        int chk = reportService.reportchkMar(ProductsNo, sid);

        boolean result;
        if(chk == 0) {
            result = true;

        } else {
            result= false;
        }

        return result;
    }

    @GetMapping("/report/getReportMar")
    public String getReportMarForm (@RequestParam("ProductsNo")int ProductsNo, Principal principal, Model model) throws Exception {

        String sid = principal != null ? principal.getName() : "";
//        Products Products = ProductsService.ProductsDetail(ProductsNo);
//
//        model.addAttribute("ProductsNo", ProductsNo);
//        model.addAttribute("Products", Products);

        int chk1 = reportService.reportchkMar(ProductsNo, sid );
        if (chk1 == 0) {
            return "report/reportMarInsert";
        } else {
            model.addAttribute("msg", "이미 신고한 회원입니다.");
            model.addAttribute("url", "/layout/alert");
            return "layout/alert";
        }

    }

    @PostMapping("/report/reportMarPro")
    public String reportMarPro (HttpServletRequest request, Principal principal){

        String sid = principal != null ? principal.getName() : "";
        int ProductsNo = Integer.parseInt(request.getParameter("ProductsNo"));
        String reporter = request.getParameter("reporter");
        String reason = request.getParameter("reason");
        String title=request.getParameter("title");

        Report report = new Report();
        report.setReporter(reporter);
        report.setLoginId(sid);
        report.setTitle(title);
        report.setReason(reason);

        reportService.reportMarInsert(report);
        return "report/reportSuc";


    }

}
