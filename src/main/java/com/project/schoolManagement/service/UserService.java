package com.project.schoolManagement.service;

import java.util.HashMap;

import com.project.schoolManagement.model.Role;
import com.project.schoolManagement.model.User;
import com.project.schoolManagement.repository.UserRepository;
import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.response.Response;
import com.project.schoolManagement.util.JwtUtil;
import com.project.schoolManagement.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRep;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<Object> userSignUp(Request req) {
        ResponseEntity<Object> res = null;
        try {
            User check = userRep.findByUserNameOrEmail(req.getUsername(), req.getEmail());
            if (check != null)
                return ResponseUtil.errorResponse(null, "Username or Email already exists", HttpStatus.valueOf(400));
            String pass = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(10));

            User user = new User(req.getUsername(), pass, req.getEmail(), new Role(req.getRole()));

            userRep.save(user);
            String jwt = jwtUtil.generateToken(req.getUsername());
            HashMap<String, Object> mp = new HashMap<>();
            mp.put("jwt", jwt);

            res = new ResponseEntity<>(mp, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

    public ResponseEntity<Object> userDetails(String username) {
        ResponseEntity<Object> res = null;
        try {
            User user = userRep.findByUserName(username);
            Response response = new Response(user, true, null);
            res = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }

}
