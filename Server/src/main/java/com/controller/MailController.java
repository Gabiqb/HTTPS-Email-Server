package com.controller;

import com.model.Mail;
import com.model.User;
import com.services.MailService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

   @PostMapping(value = "/saveMail", consumes = "application/json", produces = "application/json")
    public void sendMail(@RequestBody Mail mail){
       mailService.saveMail(mail);
   }

    @RequestMapping("/getMails/{email}")
    public List<Mail> getMails(@PathVariable("email") String email){
        return mailService.getMailsByReceiver(email);
    }

    @RequestMapping("/getSentMails/{email}")
    public List<Mail> getSentMails(@PathVariable("email") String email){
        return mailService.getMailsBySender(email);
    }

    @PostMapping(value = "/deleteMails", consumes = "application/json", produces = "application/json")
    public void deleteMails(@RequestBody List<String> mailsToDelete){
        mailService.deleteMails(mailsToDelete);
    }

}
