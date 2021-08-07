package com.project.schoolManagement.controller;

import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> userSignUp(@RequestBody Request req) {

        return userService.userSignUp(req);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> userDetails(@PathVariable("username") String username) {
        return userService.userDetails(username);
    }

}
