package com.services;

import com.misc.Roles;
import com.model.User;
import com.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Hashtable<String, User> users=new Hashtable<>();

    public User getUserByEmail(String email)
    {
        return userRepo.findByEmail(email);
    }
    public void saveUser(User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(Roles.USER);
        user.setEnabled(true);
        userRepo.save(user);
    }

}

