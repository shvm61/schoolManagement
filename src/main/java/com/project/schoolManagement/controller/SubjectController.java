package com.project.schoolManagement.controller;

import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subser;

    @PostMapping("/signup")
    public ResponseEntity<Object> sectionsignUp(@RequestBody Request req) {

        return subser.subjectSignUp(req.getSubjectName());
    }
}
