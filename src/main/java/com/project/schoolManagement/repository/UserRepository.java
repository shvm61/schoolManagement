package com.project.schoolManagement.repository;

import com.project.schoolManagement.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    User findByUserNameOrEmail(String username, String email);
}
