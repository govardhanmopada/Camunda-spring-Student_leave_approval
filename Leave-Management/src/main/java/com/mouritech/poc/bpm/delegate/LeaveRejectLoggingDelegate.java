package com.mouritech.poc.bpm.delegate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.IdentityLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class LeaveRejectLoggingDelegate implements JavaDelegate {

	private final Logger log = LoggerFactory.getLogger(LeaveRejectLoggingDelegate.class);
	
	@Inject
	IdentityService identityService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		log.info(" This service task invoked by :{}", execution.getProcessDefinitionId());
		log.info(" activityId:{} ",execution.getActivityInstanceId());
		log.info(" variable:{} ",execution.getVariables());		
		log.info(" variables names:{} ",execution.getVariableNames());
		
		//String loggedInUserName = identityService.getCurrentAuthentication().getUserId();
		
		//Set<IdentityLink> identityLinks = delegateTask.getCandidates();
		
		//log.info(" loggedInUserName:{} ",loggedInUserName);
		
		//List<String> groupIds = identityLinks.stream().map(IdentityLink::getGroupId).collect(Collectors.toList());
		


	}

}
