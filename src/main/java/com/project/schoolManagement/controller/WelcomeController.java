package com.project.schoolManagement.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.schoolManagement.request.AuthRequest;
import com.project.schoolManagement.request.TestRequest;
import com.project.schoolManagement.service.TestService;
import com.project.schoolManagement.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TestService testSer;

    @GetMapping("")
    public String welcome() {
        return "Welcome !!";
    }

    @PostMapping("/test")
    public ResponseEntity<Object> test(@RequestBody TestRequest req) {
        return testSer.test(req);
    }

    @GetMapping("/test")
    public void gettest(HttpServletResponse res) throws IOException {
        res.sendRedirect("http://localhost:8000/api");
    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(authRequest.getUsername());
        HashMap<String, Object> mp = new HashMap<>();
        mp.put("jwt", token);
        return new ResponseEntity<>(mp, HttpStatus.OK);

    }
}
