package com.mouritech.poc.bpm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="leaveRequest")
public class LeaveRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leaveRequestId;
	
	private String businessKey;
		
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
	@JoinColumn(name = "studentId", referencedColumnName = "studentId")
	private Student student;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = LeaveStatus.class)
	@JoinColumn(name = "leaveStatusId", referencedColumnName = "leaveStatusId")
	private LeaveStatus leaveStatus;
	
	private String leaveStage;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Employee.class)
	@JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
	private Employee employeeId;
	
	
	private int leaveRequestDays;

	

	public Integer getLeaveRequestId() {
		return leaveRequestId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveStage() {
		return leaveStage;
	}

	public void setLeaveStage(String leaveStage) {
		this.leaveStage = leaveStage;
	}

	public Employee getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Employee employeeId) {
		this.employeeId = employeeId;
	}

	public int getLeaveRequestDays() {
		return leaveRequestDays;
	}

	public void setLeaveRequestDays(int leaveRequestDays) {
		this.leaveRequestDays = leaveRequestDays;
	}

	public void setLeaveRequestId(Integer leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	public LeaveRequest(Integer leaveRequestId, String businessKey,
			Student student, LeaveStatus leaveStatus, String leaveStage,
			Employee employeeId, int leaveRequestDays) {
		super();
		this.leaveRequestId = leaveRequestId;
		this.businessKey = businessKey;
		this.student = student;
		this.leaveStatus = leaveStatus;
		this.leaveStage = leaveStage;
		this.employeeId = employeeId;
		this.leaveRequestDays = leaveRequestDays;
	}

	public LeaveRequest() {
		super();
	}
	
	
	
}
