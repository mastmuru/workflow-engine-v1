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

import com.fa.workflowengine.dto.AuthRoleDto;
import com.fa.workflowengine.entity.WfAuthRoleEntity;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.repository.WfAuthRoleRepository;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfAuthRoleRepository repository;

	@Autowired
	private WfModuleRepository moduleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		AuthRoleDto dto = (AuthRoleDto) t1.getRequest();
		WfAuthRoleEntity entity = new WfAuthRoleEntity();

		WfModuleEntity moduleEntity = moduleRepository.findByIdAndIsActive(dto.getModuleId(), true);
		entity.setWfModuleEntity(moduleEntity);

		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<AuthRoleDto>> response = new WfResponse<>();
		List<WfAuthRoleEntity> list = repository.findAll();
		List<AuthRoleDto> actionsDtos = new ArrayList<AuthRoleDto>();
		for (WfAuthRoleEntity wfActionsEntity : list) {
			AuthRoleDto actionsDto = new AuthRoleDto();
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
		WfResponse<AuthRoleDto> response = new WfResponse<>();
		Optional<WfAuthRoleEntity> optional = repository.findById(id);
		WfAuthRoleEntity wfActionsEntity = optional.get();
		AuthRoleDto actionsDto = new AuthRoleDto();
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
		WfResponse<List<AuthRoleDto>> response = new WfResponse<>();
		List<WfAuthRoleEntity> list = repository.findByIsActive(true);
		List<AuthRoleDto> actionsDtos = new ArrayList<AuthRoleDto>();
		for (WfAuthRoleEntity wfActionsEntity : list) {
			AuthRoleDto actionsDto = new AuthRoleDto();
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
		WfResponse<List<AuthRoleDto>> response = new WfResponse<>();
		List<WfAuthRoleEntity> list = repository.findByWfModuleEntityIdAndIsActive(id, true);
		List<AuthRoleDto> actionsDtos = new ArrayList<AuthRoleDto>();
		for (WfAuthRoleEntity wfActionsEntity : list) {
			AuthRoleDto actionsDto = new AuthRoleDto();
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
