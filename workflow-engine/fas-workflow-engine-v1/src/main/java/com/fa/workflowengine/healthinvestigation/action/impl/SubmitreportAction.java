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
* @bean ModuleId_BusinessProcessId:(1_4)
* @name Business Process : SubmitReport
* @description :SubmitReport
* @createdDate :24/06/2021 19:14:51
*/

@Service("1_4")
public class SubmitreportAction implements ActionListener  {
private static final Logger logger = LoggerFactory.getLogger(SubmitreportAction.class);

	@Override
	public WfResponse<?> action(WfRequest<?> request) {
		logger.info("start");
		WfResponse<?> wfResponse = new WfResponse<>();
		//Your Business Process steps
		logger.info("end");
		return wfResponse;
	}

}
