package com.controller;

import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup/{email}", method= RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
