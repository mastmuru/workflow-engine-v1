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

import com.fa.workflowengine.dto.ActionsDto;
import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.repository.WfActionsRepository;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("actionService")
public class ActionsServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfActionsRepository repository;

	@Autowired
	private WfModuleRepository moduleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		ActionsDto dto = (ActionsDto) t1.getRequest();
		WfActionsEntity entity = new WfActionsEntity();
		BeanUtils.copyProperties(dto, entity);

		// WfModuleEntity moduleEntity =
		// moduleRepository.findByIdAndIsActive(dto.getModuleId(), true);
		// entity.setWfModuleEntity(moduleEntity);

		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<ActionsDto>> response = new WfResponse<>();
		List<WfActionsEntity> list = repository.findAll();
		List<ActionsDto> actionsDtos = new ArrayList<ActionsDto>();
		for (WfActionsEntity wfActionsEntity : list) {
			ActionsDto actionsDto = new ActionsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
//			WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
//			if (wfModuleEntity != null) {
//				actionsDto.setModuleId(wfModuleEntity.getId());
//				actionsDto.setModuleName(wfModuleEntity.getName());
//
//			}
			actionsDtos.add(actionsDto);
		}
		response.setResponse(actionsDtos);
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<ActionsDto> response = new WfResponse<>();
		Optional<WfActionsEntity> optional = repository.findById(id);
		WfActionsEntity wfActionsEntity = optional.get();
		ActionsDto actionsDto = new ActionsDto();
		BeanUtils.copyProperties(wfActionsEntity, actionsDto);
//		WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
//
//		if (wfModuleEntity != null) {
//			actionsDto.setModuleId(wfModuleEntity.getId());
//			actionsDto.setModuleName(wfModuleEntity.getName());
//		}

		response.setResponse(actionsDto);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<ActionsDto>> response = new WfResponse<>();
		List<WfActionsEntity> list = repository.findByIsActive(true);
		List<ActionsDto> actionsDtos = new ArrayList<ActionsDto>();
		for (WfActionsEntity wfActionsEntity : list) {
			ActionsDto actionsDto = new ActionsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
//			WfModuleEntity wfModuleEntity = wfActionsEntity.getWfModuleEntity();
//
//			if (wfModuleEntity != null) {
//				actionsDto.setModuleId(wfModuleEntity.getId());
//				actionsDto.setModuleName(wfModuleEntity.getName());
//			}
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
		WfResponse<List<ActionsDto>> response = new WfResponse<>();
		List<WfActionsEntity> list = repository.findByIsActive(true);
		List<ActionsDto> actionsDtos = new ArrayList<ActionsDto>();
		for (WfActionsEntity wfActionsEntity : list) {
			ActionsDto actionsDto = new ActionsDto();
			BeanUtils.copyProperties(wfActionsEntity, actionsDto);
			WfModuleEntity wfModuleEntity = new WfModuleEntity();// wfActionsEntity.getWfModuleEntity();

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
