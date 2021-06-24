package com.mouritech.poc.bpm.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.spring.annotations.BusinessKey;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import camundajar.impl.com.google.gson.JsonObject;

import com.mouritech.poc.bpm.dto.ApproveOrRejectRequestDTO;
import com.mouritech.poc.bpm.dto.ProcessTaskResponseDTO;
import com.mouritech.poc.bpm.dto.StudentLeaveRequestDTO;
import com.mouritech.poc.bpm.model.Employee;
import com.mouritech.poc.bpm.model.LeaveRequest;
import com.mouritech.poc.bpm.model.LeaveStatus;
import com.mouritech.poc.bpm.model.Student;
import com.mouritech.poc.bpm.repository.LeaveRequestRepo;


@Service
public class CustomService {
	
	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LeaveRequestRepo leaveRequestRepo;
	
	/*@Autowired
	private RuntimeService runtimeService;*/
	
	@Value("${bpm.leaveProcessId}")
	private String leaveRequestBpmKey;
	
	@Value("${bpm.activeTask}")
	private String activeTaskUrl;
	
	@Value("${bpm.completeTask}")
	private String completeTaskUrl;
	
	
	
	private HttpHeaders headers() {
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	   return builder.build();
	}
	
	public void initiateLeaveRequestProcess(String businessKey,StudentLeaveRequestDTO studentLeaveRequest){
	
		Map<String, Object> variables = new HashMap<>();
		
		variables.put("studentId", studentLeaveRequest.getStudentId());
		variables.put("studentName", studentLeaveRequest.getStudentName());
		variables.put("leaveDaysCount", studentLeaveRequest.getLeaveDaysCount());
		
		
		processEngine.getRuntimeService().startProcessInstanceByKey(leaveRequestBpmKey,businessKey, variables);
		
		startLeaveRequestProcess(studentLeaveRequest,businessKey);
		
		
			
	}
	
	
	public void startLeaveRequestProcess(StudentLeaveRequestDTO studentLeaveRequestDTO,String businessKey){
		
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setBusinessKey(businessKey);
		leaveRequest.setLeaveStatus(new LeaveStatus(1));
		leaveRequest.setEmployeeId(new Employee(1));
		leaveRequest.setLeaveStage("class Teacher Approval pending");
		leaveRequest.setStudent(new Student(studentLeaveRequestDTO.getStudentId()));
		leaveRequest.setLeaveRequestDays(studentLeaveRequestDTO.getLeaveDaysCount());
		
		try{
		leaveRequest = leaveRequestRepo.save(leaveRequest);
		}catch(Exception e){
			System.out.println("exception===>"+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("leaveRequest====>"+leaveRequest.getLeaveRequestId());
		
		requestForwardToClassTeacher(businessKey);
	}
	

	public void employeeDecisionOnLeaveRequest(ApproveOrRejectRequestDTO approveOrRejectRequestDTO) throws Exception{
		
		try{
		Optional<LeaveRequest> leaveRequestOptional = leaveRequestRepo.findById(approveOrRejectRequestDTO.getLeaveRequestId());
		
		if(!leaveRequestOptional.isPresent()){
			throw new Exception("No record found");
		}
		
		LeaveRequest leaveRequest= leaveRequestOptional.get();
		
		leaveRequest.setBusinessKey(approveOrRejectRequestDTO.getBusinessKey());
		leaveRequest.setLeaveStage(approveOrRejectRequestDTO.getLeaveStage());
		leaveRequest.setLeaveStatus(new LeaveStatus(approveOrRejectRequestDTO.getLeaveStatusId()));
		
		leaveRequestRepo.save(leaveRequest);
		
		//Map<String, Object> variables = new HashMap<>();
		
		JSONObject variables= new JSONObject();
		
		JSONObject mainVariable= new JSONObject();
		
		JSONObject trueValue= new JSONObject();
		trueValue.put("value", true);
		
		JSONObject falseValue= new JSONObject();
		falseValue.put("value", false);
		
		
		
		if(approveOrRejectRequestDTO.getLeaveStatusId()==2){
			variables.put("approved", trueValue);
			variables.put("isFurtherApprovelRequired", falseValue);
		}
		else if(approveOrRejectRequestDTO.getLeaveStatusId()==4){
			variables.put("approved", trueValue);
			variables.put("isFurtherApprovelRequired", trueValue);
		}
		else if(approveOrRejectRequestDTO.getLeaveStatusId()==3){
			variables.put("approved", falseValue);			
		}
		
		mainVariable.put("variables", variables);
		
		completeTask(approveOrRejectRequestDTO.getBusinessKey(),mainVariable);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	public void requestForwardToClassTeacher(String businessKey){
		completeTask(businessKey,null);
	}
	
	
	public String getCurrentTaskIdByBusinessKey(String businessKey){
		
		String url = activeTaskUrl+businessKey;
				
		HttpEntity<String> entity = new HttpEntity<>(headers());
		ResponseEntity<ProcessTaskResponseDTO[]> result =
			restTemplate.exchange(url, HttpMethod.GET, entity, ProcessTaskResponseDTO[].class);
		return Objects.requireNonNull(result.getBody())[0].getId();
				
	}
	
	public void completeTask(String businessKey, JSONObject variables) {
		String url = completeTaskUrl.replace("$TASK_ID", getCurrentTaskIdByBusinessKey(businessKey));
		//variables.toString();
		HttpEntity<String> entity;
		if(null==variables)
			entity = new HttpEntity<>(headers());
		else
			entity = new HttpEntity<>(variables.toString(),headers());
		System.out.println("entity===>"+entity);
		restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	}
	
	/*private String prepareVariablesString(List<Variable> variableList) {
		Map<String, Map<String, Variable>> variables = new HashMap<>();
		Map<String, Variable> variable = new HashMap<>();
		for (Variable var : variableList) {
			variable.put(var.getKey(), var);
		}
		variables.put("variables", variable);
		try {
			return objectMapper.writeValueAsString(variables);
		} catch (JsonProcessingException e) {
			log.warn("{}", e);
		}
		return null;
	}*/
	
	
	
	

}
