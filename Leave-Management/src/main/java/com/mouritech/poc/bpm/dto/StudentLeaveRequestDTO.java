package com.mouritech.poc.bpm.dto;

public class StudentLeaveRequestDTO {

	
	private int studentLeaveId;	
	private int studentId;
	private String studentName;
	private int leaveDaysCount;
	
	
	public int getStudentLeaveId() {
		return studentLeaveId;
	}
	public void setStudentLeaveId(int studentLeaveId) {
		this.studentLeaveId = studentLeaveId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getLeaveDaysCount() {
		return leaveDaysCount;
	}
	public void setLeaveDaysCount(int leaveDaysCount) {
		this.leaveDaysCount = leaveDaysCount;
	}
}
