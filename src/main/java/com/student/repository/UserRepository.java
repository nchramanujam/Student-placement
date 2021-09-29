package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
