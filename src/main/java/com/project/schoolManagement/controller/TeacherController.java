package com.project.schoolManagement.controller;

import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.service.TeacherService;

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
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherSer;

    @PostMapping("/signup")
    public ResponseEntity<Object> teachersignUp(@RequestBody Request req) {

        return teacherSer.teacherSignUp(req);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> teacherDetails(@PathVariable("id") Long id) {
        return teacherSer.teacherDetails(id);
    }

    @GetMapping("/")
    public ResponseEntity<Object> teachersAll() {
        return teacherSer.teacherAll();
    }
}
