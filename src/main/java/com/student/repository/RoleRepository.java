package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
