package com.project.schoolManagement.repository;

import com.project.schoolManagement.model.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

}
