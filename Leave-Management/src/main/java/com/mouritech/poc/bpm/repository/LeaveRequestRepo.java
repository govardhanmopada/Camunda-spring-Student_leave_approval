package com.mouritech.poc.bpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mouritech.poc.bpm.model.LeaveRequest;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Integer> {

	
	
}
