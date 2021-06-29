package com.fa.workflowengine.healthinvestigation.action.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fa.workflowengine.action.ActionListener;

import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;

/***
* @author Muruganandam, FA Softwares
*
*/

/***
* @bean ModuleId_BusinessProcessId:(1_2)
* @name Business Process : Central Coordinator Allocation
* @description :HCM Auto allocation failed - Central Coordinator will allocate the case to State Owner
* @createdDate :24/06/2021 18:24:20
*/

@Service("1_2")
public class CentralCoordinatorAlAction implements ActionListener  {
private static final Logger logger = LoggerFactory.getLogger(CentralCoordinatorAlAction.class);

	@Override
	public WfResponse<?> action(WfRequest<?> request) {
		logger.info("start");
		WfResponse<?> wfResponse = new WfResponse<>();
		//Your Business Process steps
		logger.info("end");
		return wfResponse;
	}

}
