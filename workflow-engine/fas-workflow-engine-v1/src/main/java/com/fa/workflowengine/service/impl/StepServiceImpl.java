/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.StepsDto;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.entity.WfStatusEntity;
import com.fa.workflowengine.entity.WfStepsEntity;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.repository.WfStatusRepository;
import com.fa.workflowengine.repository.WfStepsRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("stepService")
public class StepServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfStepsRepository repository;

	@Autowired
	private WfStatusRepository statusrepository;

	@Autowired
	private WfModuleRepository moduleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		StepsDto dto = (StepsDto) t1.getRequest();
		WfStepsEntity entity = new WfStepsEntity();
		WfStatusEntity statusEntity = statusrepository.findByIdAndIsActive(dto.getStatusId(), true);
		dto.setStatusName(statusEntity.getModuleStatusName());
		dto.setStatusId(statusEntity.getModuleStatusId());
		BeanUtils.copyProperties(dto, entity);

		WfModuleEntity moduleEntity = moduleRepository.findByIdAndIsActive(dto.getModuleId(), true);
		entity.setWfModuleEntity(moduleEntity);

		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<StepsDto>> response = new WfResponse<>();
		List<WfStepsEntity> list = repository.findAll();
		List<StepsDto> actionsDtos = new ArrayList<StepsDto>();
		for (WfStepsEntity wfActionsEntity : list) {
			StepsDto actionsDto = new StepsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
			WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
			if (wfModuleEntity != null) {
				actionsDto.setModuleId(wfModuleEntity.getId());
				actionsDto.setModuleName(wfModuleEntity.getName());

			}
			actionsDtos.add(actionsDto);
		}
		response.setResponse(actionsDtos);
		return response;

	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<StepsDto> response = new WfResponse<>();
		Optional<WfStepsEntity> optional = repository.findById(id);
		WfStepsEntity wfActionsEntity = optional.get();
		StepsDto actionsDto = new StepsDto();
		BeanUtils.copyProperties(wfActionsEntity, actionsDto);
		WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();

		if (wfModuleEntity != null) {
			actionsDto.setModuleId(wfModuleEntity.getId());
			actionsDto.setModuleName(wfModuleEntity.getName());
		}

		response.setResponse(actionsDto);
		return response;

	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<StepsDto>> response = new WfResponse<>();
		List<WfStepsEntity> list = repository.findByIsActive(true);
		List<StepsDto> actionsDtos = new ArrayList<StepsDto>();
		for (WfStepsEntity wfActionsEntity : list) {
			StepsDto actionsDto = new StepsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
			WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
			if (wfModuleEntity != null) {
				actionsDto.setModuleId(wfModuleEntity.getId());
				actionsDto.setModuleName(wfModuleEntity.getName());

			}
			actionsDtos.add(actionsDto);
		}
		response.setResponse(actionsDtos);
		return response;

	}

	@Override
	public WfResponse<?> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfResponse<?> getByModuleId(Long id) {
		WfResponse<List<StepsDto>> response = new WfResponse<>();
		List<WfStepsEntity> list = repository.findByWfModuleEntityIdAndIsActive(id, true);
		List<StepsDto> actionsDtos = new ArrayList<StepsDto>();
		for (WfStepsEntity wfActionsEntity : list) {
			StepsDto actionsDto = new StepsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
			WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
			if (wfModuleEntity != null) {
				actionsDto.setModuleId(wfModuleEntity.getId());
				actionsDto.setModuleName(wfModuleEntity.getName());

			}
			actionsDtos.add(actionsDto);
		}
		response.setResponse(actionsDtos);
		return response;

	}

}
