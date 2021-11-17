package com.services;

import com.model.Mail;
import com.model.User;
import com.repositories.MailRepository;
import com.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepo;


    public List<Mail> getMailsByReceiver(String mail){
        return mailRepo.findAllMailsByReceiver(mail);
    }

    public List<Mail> getMailsBySender(String mail){
        return mailRepo.findAllMailsBySender(mail);
    }

    public void saveMail(Mail mail){
        mailRepo.save(mail);
    }

    public void deleteMails(List<String> mailsToDelete) {
        for(String id:mailsToDelete){
            mailRepo.deleteById(id);
        }
    }

}

