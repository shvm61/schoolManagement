package com.project.schoolManagement.controller;

import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studSer;

    @PostMapping("/signup")
    public ResponseEntity<Object> studentsignUp(@RequestBody Request req) {

        return studSer.studentSignUp(req);
    }

    @PostMapping("/upd")
    public ResponseEntity<Object> studentStlsUPD(@RequestBody Request req) {

        return studSer.studentDtlsUPD(req.getStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> studentDetails(@PathVariable("id") Long id) {
        return studSer.studentDetails(id);
    }

    @GetMapping("/")
    public ResponseEntity<Object> studentsAll(@RequestParam(name = "classId", required = false) Long classId) {
        System.out.println(classId);
        return studSer.studentAll(classId);
    }
}
