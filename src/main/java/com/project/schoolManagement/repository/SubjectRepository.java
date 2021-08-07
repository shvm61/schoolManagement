package com.project.schoolManagement.repository;

import com.project.schoolManagement.model.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
