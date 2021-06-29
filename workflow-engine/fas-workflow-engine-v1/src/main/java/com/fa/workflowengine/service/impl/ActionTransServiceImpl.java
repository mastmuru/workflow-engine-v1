/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.ActionTransitionsDto;
import com.fa.workflowengine.dto.BusinessProcessDto;
import com.fa.workflowengine.entity.WfActionTransitionsEntity;
import com.fa.workflowengine.entity.WfAuthRoleEntity;
import com.fa.workflowengine.entity.WfBusinessProcessEntity;
import com.fa.workflowengine.entity.WfStepsActionsEntity;
import com.fa.workflowengine.repository.WfActionTransitionsRepository;
import com.fa.workflowengine.repository.WfAuthRoleRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("actionTransService")
public class ActionTransServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfActionTransitionsRepository repository;

	@Autowired
	private WfAuthRoleRepository authRoleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		ActionTransitionsDto dto = (ActionTransitionsDto) t1.getRequest();
		WfActionTransitionsEntity entity = new WfActionTransitionsEntity();
		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<ActionTransitionsDto>> response = new WfResponse<>();
		response.setResponse(mapEntityToDto(repository.findAll()));
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<ActionTransitionsDto> response = new WfResponse<>();
		Optional<WfActionTransitionsEntity> optional = repository.findById(id);

		WfActionTransitionsEntity actionTransitionsEntity = optional.isPresent() ? optional.get()
				: new WfActionTransitionsEntity();
		ActionTransitionsDto transitionsDto = new ActionTransitionsDto();
		BeanUtils.copyProperties(actionTransitionsEntity, transitionsDto);
		transitionsDto.setActionId(actionTransitionsEntity.getActionsEntity().getId());
		transitionsDto.setSubProcessType(actionTransitionsEntity.getProcessType());
		WfBusinessProcessEntity businessProcessEntity = actionTransitionsEntity.getBusinessProcessEntity();
		BusinessProcessDto businessProcessDto = new BusinessProcessDto();
		BeanUtils.copyProperties(businessProcessEntity, businessProcessDto);
		transitionsDto.setBusinessProcessDto(businessProcessDto);
		transitionsDto.setBpId(businessProcessEntity.getId());
		transitionsDto.setBpName(businessProcessEntity.getName());

		List<WfStepsActionsEntity> stepsActionsEntities = actionTransitionsEntity.getStepsActionsEntities();

		List<WfAuthRoleEntity> rolesList = authRoleRepository.findByModuleRoleIdInAndIsActive(
				stepsActionsEntities.stream().map(WfStepsActionsEntity::getRoleId).collect(Collectors.toList()), true);

		transitionsDto.setRoleNames(rolesList.stream().map(WfAuthRoleEntity::getModuleRoleName).collect(Collectors.toList()));

		transitionsDto.setRoles(rolesList.stream().map(WfAuthRoleEntity::getId).collect(Collectors.toList()));

		response.setResponse(transitionsDto);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<WfActionTransitionsEntity>> response = new WfResponse<>();
		response.setResponse(repository.findByIsActive(true));
		return response;
	}

	@Override
	public WfResponse<?> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ActionTransitionsDto> mapEntityToDto(List<WfActionTransitionsEntity> wfActionTransitionsEntities) {
		List<ActionTransitionsDto> list = new ArrayList<ActionTransitionsDto>();

		if (wfActionTransitionsEntities != null && wfActionTransitionsEntities.size() > 0) {
			for (WfActionTransitionsEntity actionTransitionsEntity : wfActionTransitionsEntities) {
				ActionTransitionsDto transitionsDto = new ActionTransitionsDto();
				BeanUtils.copyProperties(actionTransitionsEntity, transitionsDto);
				transitionsDto.setActionId(actionTransitionsEntity.getActionsEntity().getId());
				transitionsDto.setSubProcessType(actionTransitionsEntity.getProcessType());
				WfBusinessProcessEntity businessProcessEntity = actionTransitionsEntity.getBusinessProcessEntity();
				transitionsDto.setBpId(businessProcessEntity.getId());
				transitionsDto.setBpName(businessProcessEntity.getName());
				list.add(transitionsDto);
			}
		}
		return list;

	}

	@Override
	public WfResponse<?> getByModuleId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
