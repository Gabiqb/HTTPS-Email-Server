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

    public void saveMail(Mail mail){
        mailRepo.save(mail);
    }

}

