package com.mouritech.poc.bpm.dto;

public class ApproveOrRejectRequestDTO {
	
	private Integer leaveRequestId;
	private Integer employeeId;
	private Integer leaveStatusId;
	private String leaveStage;
	private String businessKey;
	
	public Integer getLeaveRequestId() {
		return leaveRequestId;
	}
	public void setLeaveRequestId(Integer leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getLeaveStatusId() {
		return leaveStatusId;
	}
	public void setLeaveStatusId(Integer leaveStatusId) {
		this.leaveStatusId = leaveStatusId;
	}
	public String getLeaveStage() {
		return leaveStage;
	}
	public void setLeaveStage(String leaveStage) {
		this.leaveStage = leaveStage;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	
	
}
