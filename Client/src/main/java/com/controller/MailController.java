package com.controller;

import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

public class MailController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mail() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = restTemplate.getForObject("https://localhost:8082/get/" + auth.getName(), User.class);
        modelAndView.addObject("currentUser", user);
        if (user != null) {
            modelAndView.addObject("fullName", "Welcome " + user.getName());
            modelAndView.setViewName("mail");
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
