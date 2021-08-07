package com.project.schoolManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.project.schoolManagement.model.Teacher;
import com.project.schoolManagement.model.User;
import com.project.schoolManagement.repository.TeacherRepo;
import com.project.schoolManagement.repository.UserRepository;
import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.util.JwtUtil;
import com.project.schoolManagement.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teachRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<Object> teacherSignUp(Request req) {
        ResponseEntity<Object> res = null;
        try {

            User user = userRepo.findByUserName(req.getUsername());
            if (user == null) {
                return ResponseUtil.errorResponse(null, "User not found", HttpStatus.valueOf(404));
            }
            if (user.getRole().getId() != 2) {
                return ResponseUtil.errorResponse(null, "Username not associated with Teacher",
                        HttpStatus.valueOf(404));
            }
            String jwt = jwtUtil.generateToken(req.getUsername());

            Teacher teach = new Teacher(req.getFirstName(), req.getLastName(), req.getMob(), user, null);
            teachRepo.save(teach);
            HashMap<String, Object> mp = new HashMap<>();
            mp.put("jwt", jwt);
            res = ResponseUtil.successResponse(mp, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

    public ResponseEntity<Object> teacherDetails(Long id) {
        ResponseEntity<Object> res = null;
        try {
            Optional<Teacher> teach = teachRepo.findById(id);

            if (teach.isEmpty()) {
                return ResponseUtil.errorResponse(null, "User not found", HttpStatus.valueOf(404));
            }
            res = ResponseUtil.successResponse(teach, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }

    public ResponseEntity<Object> teacherAll() {
        ResponseEntity<Object> res = null;
        try {
            List<Teacher> teach = teachRepo.findAll();

            if (teach.isEmpty()) {
                return ResponseUtil.errorResponse(null, "No teacher found", HttpStatus.valueOf(404));
            }
            res = ResponseUtil.successResponse(teach, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }
}
