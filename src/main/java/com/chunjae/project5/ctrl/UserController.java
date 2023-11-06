package com.chunjae.project5.ctrl;

import com.chunjae.project5.biz.UserService;
import com.chunjae.project5.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping( "/user/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @GetMapping("/user/term")
    public ModelAndView getTermPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/term");
        return modelAndView;
    }

    @GetMapping("/user/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user/registration");

        return modelAndView;
    }

    @PostMapping("/user/registration")
    public ModelAndView getRegistrationPagePro(User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByLoginId(user.getLoginId());
        if (userExists != null) {
            bindingResult
                    .rejectValue("loginId", "error.loginId","사용이 불가한 아이디입니다.");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/user/join");
        } else {
            userService.saveUser(user);

            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }





}
