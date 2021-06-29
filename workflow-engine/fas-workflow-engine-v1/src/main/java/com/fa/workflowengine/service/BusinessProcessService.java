/**
 * 
 */
package com.fa.workflowengine.service;

import java.util.List;

import com.fa.workflowengine.dto.ActionTransitionsDto;
import com.fa.workflowengine.dto.BusinessProcessDto;
import com.fa.workflowengine.entity.WfBusinessProcessEntity;
import com.fa.workflowengine.reqres.dto.WfResponse;

/**
 * @author Muruganandam, FA Softwares
 *
 */
public interface BusinessProcessService {

	WfResponse<?> createBusinessProcess(BusinessProcessDto businessProcessDto);

	WfResponse<?> getBusinessProcesses();

	WfResponse<?> getBusinessProcesse(Long id);

	WfResponse<?> saveTask(ActionTransitionsDto actionTransitionsDto);

	List<ActionTransitionsDto> getActionTrans(WfBusinessProcessEntity businessProcessEntity);

	WfResponse<?> getBusinessProcessByModuleId(Long modId);
}
