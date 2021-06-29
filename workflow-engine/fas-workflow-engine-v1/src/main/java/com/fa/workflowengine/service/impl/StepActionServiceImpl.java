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

import com.fa.workflowengine.dto.StepsActionsDto;
import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfAuthRoleEntity;
import com.fa.workflowengine.entity.WfStepsActionsEntity;
import com.fa.workflowengine.entity.WfStepsEntity;
import com.fa.workflowengine.repository.WfActionsRepository;
import com.fa.workflowengine.repository.WfAuthRoleRepository;
import com.fa.workflowengine.repository.WfStepsActionsRepository;
import com.fa.workflowengine.repository.WfStepsRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("stepActionService")
public class StepActionServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfStepsActionsRepository repository;

	@Autowired
	private WfStepsRepository stepsRepository;

	@Autowired
	private WfActionsRepository actionsRepository;

	@Autowired
	private WfAuthRoleRepository authRoleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		StepsActionsDto dto = (StepsActionsDto) t1.getRequest();
		WfStepsActionsEntity entity = new WfStepsActionsEntity();
		BeanUtils.copyProperties(dto, entity);

		WfStepsEntity stepsEntity = stepsRepository.findByIdAndIsActive(dto.getStepId(), true);
		WfActionsEntity actionsEntity = actionsRepository.findByIdAndIsActive(dto.getActionId(), true);
		WfAuthRoleEntity roleEntity = authRoleRepository.findByIdAndIsActive(dto.getRoleId(), true);

		entity.setStepsEntity(stepsEntity);
		entity.setActionsEntity(actionsEntity);
		// entity.setAuthRoleEntity(roleEntity);
		entity.setRoleId(roleEntity.getModuleRoleId());

		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<StepsActionsDto>> response = new WfResponse<>();
		List<StepsActionsDto> list = new ArrayList<StepsActionsDto>();
		List<WfStepsActionsEntity> findAll = repository.findByIsActive(true);
		for (WfStepsActionsEntity wfStepsActionsEntity : findAll) {
			StepsActionsDto actionsDto = new StepsActionsDto();
			BeanUtils.copyProperties(wfStepsActionsEntity, actionsDto);

			WfStepsEntity stepsEntity = wfStepsActionsEntity.getStepsEntity();
			WfActionsEntity actionsEntity = wfStepsActionsEntity.getActionsEntity();
			// WfAuthRoleEntity authRoleEntity = wfStepsActionsEntity.getAuthRoleEntity();
			WfAuthRoleEntity authRoleEntity = authRoleRepository
					.findByModuleRoleIdAndIsActive(wfStepsActionsEntity.getRoleId(), true);

			actionsDto.setActionId(actionsEntity != null ? actionsEntity.getId() : null);
			actionsDto.setRoleId(authRoleEntity != null ? authRoleEntity.getModuleRoleId() : null);
			actionsDto.setStepId(stepsEntity != null ? stepsEntity.getId() : null);

			actionsDto.setActionName(actionsEntity.getDisplayName());
			actionsDto.setRoleName(authRoleEntity.getModuleRoleName());
			actionsDto.setStepName(stepsEntity.getDescription());

			list.add(actionsDto);
		}

		response.setResponse(list);
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<StepsActionsDto> response = new WfResponse<>();
		Optional<WfStepsActionsEntity> optional = repository.findById(id);

		if (optional.isPresent()) {
			WfStepsActionsEntity wfStepsActionsEntity = optional.get();
			StepsActionsDto actionsDto = new StepsActionsDto();
			BeanUtils.copyProperties(wfStepsActionsEntity, actionsDto);

			WfStepsEntity stepsEntity = wfStepsActionsEntity.getStepsEntity();
			WfActionsEntity actionsEntity = wfStepsActionsEntity.getActionsEntity();
			// WfAuthRoleEntity authRoleEntity = wfStepsActionsEntity.getAuthRoleEntity();
			WfAuthRoleEntity authRoleEntity = authRoleRepository
					.findByModuleRoleIdAndIsActive(wfStepsActionsEntity.getRoleId(), true);

			actionsDto.setActionId(actionsEntity.getId());
			actionsDto.setRoleId(authRoleEntity.getModuleRoleId());
			actionsDto.setStepId(stepsEntity.getId());

			actionsDto.setActionName(actionsEntity.getDisplayName());
			actionsDto.setRoleName(authRoleEntity.getModuleRoleName());
			actionsDto.setStepName(stepsEntity.getDescription());

			response.setResponse(actionsDto);
		}
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<StepsActionsDto>> response = new WfResponse<>();
		List<StepsActionsDto> list = new ArrayList<StepsActionsDto>();
		List<WfStepsActionsEntity> findAll = repository.findByIsActive(true);
		for (WfStepsActionsEntity wfStepsActionsEntity : findAll) {
			StepsActionsDto actionsDto = new StepsActionsDto();
			BeanUtils.copyProperties(wfStepsActionsEntity, actionsDto);

			WfStepsEntity stepsEntity = wfStepsActionsEntity.getStepsEntity();
			WfActionsEntity actionsEntity = wfStepsActionsEntity.getActionsEntity();
			// WfAuthRoleEntity authRoleEntity = wfStepsActionsEntity.getAuthRoleEntity();
			WfAuthRoleEntity authRoleEntity = authRoleRepository.findByIdAndIsActive(wfStepsActionsEntity.getRoleId(),
					true);

			actionsDto.setActionId(actionsEntity.getId());
			actionsDto.setRoleId(authRoleEntity.getModuleRoleId());
			actionsDto.setStepId(stepsEntity.getId());

			actionsDto.setActionName(actionsEntity.getDisplayName());
			actionsDto.setRoleName(authRoleEntity.getModuleRoleName());
			actionsDto.setStepName(stepsEntity.getDescription());

			list.add(actionsDto);
		}

		response.setResponse(list);
		return response;
	}

	@Override
	public WfResponse<?> deleteById(Long id) {
		WfResponse<String> response = new WfResponse<String>();
		repository.deleteById(id);
		return response;
	}

	@Override
	public WfResponse<?> getByModuleId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
