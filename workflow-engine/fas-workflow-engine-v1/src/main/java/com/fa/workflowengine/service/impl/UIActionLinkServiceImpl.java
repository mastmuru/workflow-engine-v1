/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.UIActionLinkDto;
import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfAuthRoleEntity;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.entity.WfStatusEntity;
import com.fa.workflowengine.entity.WfUIActionOrLinkEntity;
import com.fa.workflowengine.repository.WfActionsRepository;
import com.fa.workflowengine.repository.WfAuthRoleRepository;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.repository.WfStatusRepository;
import com.fa.workflowengine.repository.WfUIActionOrLinkRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("uiActionLinkService")
public class UIActionLinkServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfUIActionOrLinkRepository repository;

	@Autowired
	private WfActionsRepository actionsRepository;

	@Autowired
	private WfAuthRoleRepository authRoleRepository;

	@Autowired
	private WfStatusRepository statusRepository;

	@Autowired
	private WfModuleRepository modRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		UIActionLinkDto dto = (UIActionLinkDto) t1.getRequest();

		WfUIActionOrLinkEntity entity = new WfUIActionOrLinkEntity();

		BeanUtils.copyProperties(dto, entity);

		entity.setCreatedDate(new Date());

		WfStatusEntity statusEntity = statusRepository.findByIdAndIsActive(dto.getStatus(), true);
		WfActionsEntity actionsEntity = actionsRepository.findByIdAndIsActive(dto.getActionId(), true);
		WfAuthRoleEntity roleEntity = authRoleRepository.findByIdAndIsActive(dto.getRoleId(), true);
		WfModuleEntity moduleEntity = modRepository.findByIdAndIsActive(dto.getModuleId(), true);

		entity.setWfRoleId(dto.getRoleId());
		entity.setWfStatusId(dto.getStatus());
		entity.setActionId(actionsEntity.getId());
		entity.setActionName(actionsEntity.getDisplayName());
		entity.setStatus(statusEntity.getModuleStatusId());
		entity.setStatusName(statusEntity.getModuleStatusName());
		entity.setRoleId(roleEntity.getModuleRoleId());
		entity.setRoleName(roleEntity.getModuleRoleName());
		entity.setModuleId(moduleEntity.getId());
		entity.setModuleName(moduleEntity.getName());

		entity = repository.save(entity);
		response.setAckNo(String.valueOf(entity.getId()));
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<WfUIActionOrLinkEntity>> response = new WfResponse<>();

		repository.findByRoleIdAndPageCodeAndStatusAndIsActiveOrderByOrderNoAsc(13l, "ReportPage", 87l, true);

		response.setResponse(repository.findAll());
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<WfUIActionOrLinkEntity> response = new WfResponse<>();
		Optional<WfUIActionOrLinkEntity> optional = repository.findById(id);
		response.setResponse(optional.isPresent() ? optional.get() : null);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<WfUIActionOrLinkEntity>> response = new WfResponse<>();
		response.setResponse(repository.findByIsActive(true));
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
