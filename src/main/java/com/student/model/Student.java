package com.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "author_generator")
	    @SequenceGenerator(name="author_generator", sequenceName = "student_seq", allocationSize = 1)
	    private Long id;
	 
	 	private Long rollno;
	 	private Long percentage;
	 	private Long backlogs;
	 	@Column(name = "FATHERNAME")
	 	private String fathername;
	 	@Column(name = "EMAIL")
	 	private String email;
	 	@Column(name = "PHONE")
	 	private Long phone;
	 	private String studentname;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getRollno() {
			return rollno;
		}
		public void setRollno(Long rollno) {
			this.rollno = rollno;
		}
		public Long getPercentage() {
			return percentage;
		}
		public void setPercentage(Long percentage) {
			this.percentage = percentage;
		}
		public Long getBacklogs() {
			return backlogs;
		}
		public void setBacklogs(Long backlogs) {
			this.backlogs = backlogs;
		}
		public String getFathername() {
			return fathername;
		}
		public void setFathername(String fathername) {
			this.fathername = fathername;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Long getPhone() {
			return phone;
		}
		public void setPhone(Long phone) {
			this.phone = phone;
		}
		public String getStudentname() {
			return studentname;
		}
		public void setStudentname(String studentname) {
			this.studentname = studentname;
		}
	 	
	 	
	 	

}
