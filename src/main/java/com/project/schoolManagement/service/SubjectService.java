package com.project.schoolManagement.service;

import com.project.schoolManagement.model.Subject;
import com.project.schoolManagement.repository.SubjectRepository;
import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subRepo;

    public ResponseEntity<Object> subjectSignUp(String subjectName) {
        ResponseEntity<Object> res = null;
        try {
            Subject sub = new Subject(subjectName);
            subRepo.save(sub);

            res = ResponseUtil.successResponse(sub, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

}
