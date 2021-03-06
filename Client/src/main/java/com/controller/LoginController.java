package com.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    private Authentication auth;
    @GetMapping("/login")
    public ModelAndView login() throws Exception {

       auth = SecurityContextHolder.getContext().getAuthentication();
       if(auth!=null) {
           if (!auth.getPrincipal().toString().equals("anonymousUser")) {
               ModelAndView modelAndView = new ModelAndView();
               modelAndView.setViewName("redirect:mail");
               return modelAndView;
           } else {

               ModelAndView modelAndView = new ModelAndView();
               modelAndView.setViewName("login");
               return modelAndView;
           }
       }
       else
           return null;
    }
    @GetMapping(value = {"/"})
    public ModelAndView home() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            if (!auth.getPrincipal().toString().equals("anonymousUser")) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:mail");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("login");
                return modelAndView;
            }
        }
        else
            return null;
    }

    @GetMapping("/login?=error")
    public ModelAndView loginErr() {

        auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            if (!auth.getPrincipal().toString().equals("anonymousUser")) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:mail");
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:login");
                return modelAndView;
            }
        }
        else
            return null;
    }


}
