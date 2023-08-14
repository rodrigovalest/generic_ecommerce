package com.learn.ecommerce.controller;

import com.learn.ecommerce.model.user.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView index(Authentication authentication) {
        return new ModelAndView("home");
    }

    @GetMapping("/profile")
    public ModelAndView profile(Authentication authentication) {
        UserModel user = (UserModel) authentication.getPrincipal();

        ModelAndView mv = new ModelAndView("profile");
        mv.addObject("user", user);
        return mv;
    }
}
