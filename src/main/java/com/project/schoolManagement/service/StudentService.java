package com.project.schoolManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.project.schoolManagement.model.Section;
import com.project.schoolManagement.model.Student;
import com.project.schoolManagement.model.User;
import com.project.schoolManagement.repository.StudentRepo;
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
public class StudentService {
    @Autowired
    private StudentRepo studRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<Object> studentSignUp(Request req) {
        ResponseEntity<Object> res = null;
        try {

            User user = userRepo.findByUserName(req.getUsername());
            if (user == null) {
                return ResponseUtil.errorResponse(null, "User not found", HttpStatus.valueOf(404));
            }
            if (user.getRole().getId() != 3) {
                return ResponseUtil.errorResponse(null, "Username not associated with student",
                        HttpStatus.valueOf(404));
            }
            String jwt = jwtUtil.generateToken(req.getUsername());

            Student stud = new Student(req.getFirstName(), req.getLastName(), req.getMob(), user, null);
            studRepo.save(stud);
            HashMap<String, Object> mp = new HashMap<>();
            mp.put("jwt", jwt);
            res = ResponseUtil.successResponse(mp, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;

    }

    public ResponseEntity<Object> studentDetails(Long id) {
        ResponseEntity<Object> res = null;
        try {
            Optional<Student> stud = studRepo.findById(id);

            if (stud.isEmpty()) {
                return ResponseUtil.errorResponse(null, "User not found", HttpStatus.valueOf(404));
            }
            res = ResponseUtil.successResponse(stud, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }

    public ResponseEntity<Object> studentDtlsUPD(Student st) {
        ResponseEntity<Object> res = null;
        try {
            Student stud = studRepo.getById(st.getId());
            // TODO update with checks like
            // stud.setFirstName(st.getFirstName());
            studRepo.save(stud);
            res = ResponseUtil.successResponse(stud, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }

    public ResponseEntity<Object> studentAll(Long classId) {
        ResponseEntity<Object> res = null;
        try {
            if (classId != null) {

                List<Student> stud = studRepo.findBySection(Section.builder().id(classId).build());

                // stud.forEach(el -> {
                // el.getUser().getEmail();
                // });

                res = ResponseUtil.successResponse(stud, HttpStatus.OK);
                return res;
            }
            List<Student> stud = studRepo.findAll();

            if (stud.isEmpty()) {
                return ResponseUtil.errorResponse(null, "No students found", HttpStatus.valueOf(404));
            }
            res = ResponseUtil.successResponse(stud, HttpStatus.OK);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return ResponseUtil.errorResponse(null, e.getRootCause().getMessage(), HttpStatus.valueOf(500));
        }
        return res;
    }
}
