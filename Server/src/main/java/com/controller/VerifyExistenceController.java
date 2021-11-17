package com.controller;

import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyExistenceController {
    @Autowired
    private UserService userService;
    @RequestMapping("{email}")
    public HttpStatus checkUser(@PathVariable("email") String email){
        User u=userService.getUserByEmail(email);
        if(u==null)
            return HttpStatus.ACCEPTED;
        else
            return HttpStatus.IM_USED;

    }
}
