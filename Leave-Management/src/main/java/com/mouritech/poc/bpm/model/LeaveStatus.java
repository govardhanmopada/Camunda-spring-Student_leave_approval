package com.mouritech.poc.bpm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leaveStatus")
public class LeaveStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer leaveStatusId;
	private String status;
	
	public Integer getLeaveStatusId() {
		return leaveStatusId;
	}
	public void setLeaveStatusId(Integer leaveStatusId) {
		this.leaveStatusId = leaveStatusId;
	}
	public String getLeaveStatus() {
		return status;
	}
	public void setLeaveStatus(String status) {
		this.status = status;
	}
	
	public LeaveStatus(Integer leaveStatusId) {
		super();
		this.leaveStatusId = leaveStatusId;
	}
	public LeaveStatus(Integer leaveStatusId, String status) {
		super();
		this.leaveStatusId = leaveStatusId;
		this.status = status;
	}
	public LeaveStatus() {
		super();
	}
	
	

}
