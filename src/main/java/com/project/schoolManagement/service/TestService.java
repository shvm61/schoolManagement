package com.project.schoolManagement.service;

import java.util.List;

import com.project.schoolManagement.repository.StudentRepo;
import com.project.schoolManagement.repository.StudentRepo.NameOnly;
import com.project.schoolManagement.request.TestRequest;
import com.project.schoolManagement.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private StudentRepo studRepo;

    public ResponseEntity<Object> test(TestRequest req) {
        ResponseEntity<Object> res = null;
        try {

            NameOnly names = studRepo.getStudentById(req.getTestId());
            res = ResponseUtil.successResponse(names, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }
}
