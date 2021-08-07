package com.project.schoolManagement.service;

import java.util.List;
import java.util.Optional;

import com.project.schoolManagement.model.Section;
import com.project.schoolManagement.model.Teacher;
import com.project.schoolManagement.repository.SectionRepository;
import com.project.schoolManagement.repository.TeacherRepo;
import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    SectionRepository secRepo;
    @Autowired
    TeacherRepo teachRepo;

    public ResponseEntity<Object> sectionSignUp(Request req) {
        ResponseEntity<Object> res = null;
        try {
            if (teachRepo.getById(req.getTeacherId()) == null) {
                return ResponseUtil.errorResponse(null, "Invalid Teacher", HttpStatus.valueOf(404));
            }

            Section sec = Section.builder().classTeacher(Teacher.builder().id(req.getTeacherId()).build())
                    .name(req.getClassName()).build();
            secRepo.save(sec);
            res = ResponseUtil.successResponse(sec, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

    public ResponseEntity<Object> sectionAll() {
        ResponseEntity<Object> res = null;
        try {

            List<Section> sec = secRepo.findAll();

            res = ResponseUtil.successResponse(sec, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

    public ResponseEntity<Object> sectionDetails(Long id) {
        ResponseEntity<Object> res = null;
        try {

            Optional<Section> sec = secRepo.findById(id);

            res = ResponseUtil.successResponse(sec, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }
}
