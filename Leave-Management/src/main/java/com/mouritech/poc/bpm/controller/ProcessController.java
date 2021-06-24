package com.mouritech.poc.bpm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouritech.poc.bpm.dto.ApproveOrRejectRequestDTO;
import com.mouritech.poc.bpm.dto.StudentLeaveRequestDTO;
import com.mouritech.poc.bpm.service.CustomService;



@RestController
@RequestMapping("api/v1/bpmFlow")
public class ProcessController {
	
	@Autowired
	private CustomService customService;

	@PostMapping("/leaveRequest/{businessProcessKey}")
	public ResponseEntity<String> processStart(@PathVariable("businessProcessKey") String businessProcessKey,
			@RequestBody StudentLeaveRequestDTO studentLeaveRequest){
		customService.initiateLeaveRequestProcess(businessProcessKey,studentLeaveRequest);
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
	@PostMapping("/leaveRequest/approval/decision")
	public ResponseEntity<String> approveOrRejectLeaveRequest(@RequestBody ApproveOrRejectRequestDTO approveOrRejectRequestDTO) throws Exception{
		
		customService.employeeDecisionOnLeaveRequest(approveOrRejectRequestDTO);
		
		return new ResponseEntity<>("success", HttpStatus.CREATED);
	}
	
}
