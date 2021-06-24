package com.mouritech.poc.bpm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;
	private String employeeName;
	private Integer roleId;
	private Integer userId;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	public Employee(Integer employeeId) {
		super();
		this.employeeId = employeeId;
	}
	public Employee(Integer employeeId, String employeeName, Integer roleId,
			Integer userId) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.roleId = roleId;
		this.userId = userId;
	}
	public Employee() {
		super();
	}
	
	
	
	
}
