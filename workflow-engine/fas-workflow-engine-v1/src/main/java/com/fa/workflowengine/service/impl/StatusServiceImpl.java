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

import com.fa.workflowengine.dto.StatusDto;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.entity.WfStatusEntity;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.repository.WfStatusRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("statusService")
public class StatusServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfStatusRepository repository;

	@Autowired
	private WfModuleRepository moduleRepository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		StatusDto dto = (StatusDto) t1.getRequest();
		WfStatusEntity entity = new WfStatusEntity();
		BeanUtils.copyProperties(dto, entity);

		WfModuleEntity moduleEntity = moduleRepository.findByIdAndIsActive(dto.getModuleId(), true);
		entity.setWfModuleEntity(moduleEntity);

		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<StatusDto>> response = new WfResponse<>();
		List<WfStatusEntity> list = repository.findAll();
		List<StatusDto> actionsDtos = new ArrayList<StatusDto>();
		for (WfStatusEntity wfActionsEntity : list) {
			StatusDto actionsDto = new StatusDto();
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
		WfResponse<StatusDto> response = new WfResponse<>();
		Optional<WfStatusEntity> optional = repository.findById(id);
		WfStatusEntity wfActionsEntity = optional.get();
		StatusDto actionsDto = new StatusDto();
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
		WfResponse<List<StatusDto>> response = new WfResponse<>();
		List<WfStatusEntity> list = repository.findByIsActive(true);
		List<StatusDto> actionsDtos = new ArrayList<StatusDto>();
		for (WfStatusEntity wfActionsEntity : list) {
			StatusDto actionsDto = new StatusDto();
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
		WfResponse<List<StatusDto>> response = new WfResponse<>();
		List<WfStatusEntity> list = repository.findByWfModuleEntityIdAndIsActive(id, true);
		List<StatusDto> actionsDtos = new ArrayList<StatusDto>();
		for (WfStatusEntity wfActionsEntity : list) {
			StatusDto actionsDto = new StatusDto();
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
