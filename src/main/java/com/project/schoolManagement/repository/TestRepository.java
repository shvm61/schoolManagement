package com.project.schoolManagement.repository;

import java.util.UUID;

import com.project.schoolManagement.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, UUID> {

}
