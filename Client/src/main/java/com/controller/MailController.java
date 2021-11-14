package com.controller;

import com.model.Mail;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MailController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public ModelAndView mail() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = restTemplate.getForObject("https://localhost:8082/get/" + auth.getName(), User.class);
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            modelAndView.setViewName("mail");
            modelAndView.addObject("currentUser",user);
            modelAndView.addObject("composedMail",new Mail());
            ArrayList<Mail> mails = restTemplate.getForObject("https://localhost:8082/getMails/"+user.getEmail(),ArrayList.class);
            modelAndView.addObject("mails",mails);
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/sendMail")
    public ModelAndView sendMail(Mail composedMail) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = restTemplate.getForObject("https://localhost:8082/get/" + auth.getName(), User.class);
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            modelAndView.setViewName("redirect:/mail");
            modelAndView.addObject("currentUser",user);
            composedMail.setFrom(user.getEmail());
            restTemplate.postForObject("https://localhost:8082/saveMail",composedMail, Mail.class);

        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }
}
