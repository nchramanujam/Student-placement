package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.student.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	@Query(value="select email from student where percentage>=:percentage",nativeQuery=true)
	List<String> findByEmail(int percentage);
}
