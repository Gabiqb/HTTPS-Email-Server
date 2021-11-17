package com.controller;

import com.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SignUpController {
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public Object signup() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:mail");
            return modelAndView;

        } else
        {
            ModelAndView modelAndView = new ModelAndView();
            User user = new User();
            modelAndView.addObject("user", user);
            modelAndView.addObject("fullName", new String());
            modelAndView.setViewName("signup");
            return modelAndView;
        }
    }

}
