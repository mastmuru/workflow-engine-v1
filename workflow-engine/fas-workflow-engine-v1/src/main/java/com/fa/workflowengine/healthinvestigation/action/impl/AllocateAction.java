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
* @bean ModuleId_BusinessProcessId:(1_3)
* @name Business Process : Allocate
* @description :Allocate Case to Investigator or State owner by QC2, QC3 or state owner himself
* @createdDate :24/06/2021 18:26:28
*/

@Service("1_3")
public class AllocateAction implements ActionListener  {
private static final Logger logger = LoggerFactory.getLogger(AllocateAction.class);

	@Override
	public WfResponse<?> action(WfRequest<?> request) {
		logger.info("start");
		WfResponse<?> wfResponse = new WfResponse<>();
		//Your Business Process steps
		logger.info("end");
		return wfResponse;
	}

}
