package com.mouritech.poc.bpm.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_details")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	private String studentName;
	private String branch;
	private Integer userId;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Student(Integer studentId) {
		super();
		this.studentId = studentId;
	}
	public Student(Integer studentId, String studentName, String branch,
			Integer userId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.branch = branch;
		this.userId = userId;
	}
	public Student() {
		super();
	}
	
	
}
