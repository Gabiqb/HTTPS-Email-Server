package com.controller;

import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get/{email}")
    public User returnUser(@PathVariable("email") String email)
    {
        if(userService.getUserByEmail(email)!=null)
            return userService.getUserByEmail(email);
        else
            return null;
    }
}
