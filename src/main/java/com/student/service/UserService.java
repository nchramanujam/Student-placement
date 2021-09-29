package com.student.service;

import com.student.model.Student;
import com.student.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    void saveStudent(Student student);
     
    void sendEmail(String companyname,int percentage);
}
