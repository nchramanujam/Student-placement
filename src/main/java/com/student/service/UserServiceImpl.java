package com.student.service;

import com.student.model.Student;
import com.student.model.User;
import com.student.repository.RoleRepository;
import com.student.repository.StudentRepo;
import com.student.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	@Transactional
	public void saveStudent(Student student) {
       studentRepo.save(student);		
	}
	
	@Override
	public void sendEmail(String companyname,int percentage) {

	        SimpleMailMessage msg = new SimpleMailMessage();
	        List<String> email = studentRepo.findByEmail(percentage);
	        System.out.println(email.get(1));
	        for(int i=0;i<email.size();i++) {
	        	String emailAddress = email.get(i);
			  msg.setTo(emailAddress);
			  
			  msg.setSubject("Placement Drive Details");
			  msg.setText("Hi Students, /n");
			  msg.setText("Good Day!../n");
			  msg.setText("We have campus drive for");
			  msg.setText(companyname);
			  msg.setText(", Please try to attend the campus Drive /n");
			  msg.setText("Please contact for more details admin.. /n");
			  msg.setText("Thanks, /n");
			  msg.setText("Admin, /n");
			  
			  javaMailSender.send(msg);
	        }
	    }
}
