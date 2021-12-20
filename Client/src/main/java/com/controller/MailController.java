package com.controller;

import com.model.Mail;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
            ArrayList<Mail> sentMails = restTemplate.getForObject("https://localhost:8082/getSentMails/"+user.getEmail(),ArrayList.class);
            modelAndView.addObject("sentMails", sentMails);
            ArrayList<Mail> draftMails = restTemplate.getForObject("https://localhost:8082/getDraftMails/"+user.getEmail(),ArrayList.class);
            modelAndView.addObject("draftMails", draftMails);
        } else {
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/sendMail",params="action")
    public ModelAndView sendMail(Mail composedMail, @RequestParam(value="action") String action) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = restTemplate.getForObject("https://localhost:8082/get/" + auth.getName(), User.class);
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            modelAndView.setViewName("redirect:mail");
            modelAndView.addObject("currentUser",user);
            composedMail.setFrom(user.getEmail());
            if(action.equals("draft"))
                composedMail.setDraft(true);
            else
                composedMail.setDraft(false);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            composedMail.setDate(dateFormat.format(cal.getTime()));
            restTemplate.postForObject("https://localhost:8082/saveMail",composedMail, Mail.class);

        } else {
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }
}
