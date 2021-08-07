package com.project.schoolManagement.controller;

import com.project.schoolManagement.request.Request;
import com.project.schoolManagement.service.SectionService;

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
@RequestMapping("/api/section")
public class SectionController {
    @Autowired
    private SectionService secSer;

    @PostMapping("/signup")
    public ResponseEntity<Object> sectionsignUp(@RequestBody Request req) {

        return secSer.sectionSignUp(req);
    }

    @GetMapping("/")
    public ResponseEntity<Object> sectionsAll() {

        return secSer.sectionAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> sectionsDtls(@PathVariable Long id) {

        return secSer.sectionDetails(id);
    }
}
