package com.project.schoolManagement.request;

import com.project.schoolManagement.model.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    private String username;
    private String password;
    private String email;
    private Long role;
    private String firstName;
    private String lastName;
    private String mob;
    private Long section;
    private String className;
    private Long teacherId;
    private Student student;
    private String subjectName;

}