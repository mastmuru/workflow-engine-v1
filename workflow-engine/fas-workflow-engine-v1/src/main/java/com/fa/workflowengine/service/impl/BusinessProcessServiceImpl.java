/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.ActionTransitionsDto;
import com.fa.workflowengine.dto.BusinessProcessDto;
import com.fa.workflowengine.dto.RulesDto;
import com.fa.workflowengine.entity.WfActionTransitionsEntity;
import com.fa.workflowengine.entity.WfActionsEntity;
import com.fa.workflowengine.entity.WfAuthRoleEntity;
import com.fa.workflowengine.entity.WfBusinessProcessEntity;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.entity.WfRulesEntity;
import com.fa.workflowengine.entity.WfStepsActionsEntity;
import com.fa.workflowengine.entity.WfStepsEntity;
import com.fa.workflowengine.repository.WfActionTransitionsRepository;
import com.fa.workflowengine.repository.WfActionsRepository;
import com.fa.workflowengine.repository.WfAuthRoleRepository;
import com.fa.workflowengine.repository.WfBusinessProcessRepository;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.repository.WfStepsActionsRepository;
import com.fa.workflowengine.repository.WfStepsRepository;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.BusinessProcessService;
import com.fa.workflowengine.utils.CommonUtils;
import com.fa.workflowengine.utils.DateUtils;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service
public class BusinessProcessServiceImpl extends DynamicFile implements BusinessProcessService {

	@Autowired
	private WfBusinessProcessRepository businessProcessRepository;

	@Autowired
	private WfActionTransitionsRepository wfActionTransitionsRepository;

	@Autowired
	private WfStepsRepository wfStepsRepository;

	@Autowired
	private WfActionsRepository actionsRepository;

	@Autowired
	private WfModuleRepository moduleRepository;

	@Autowired
	private WfStepsRepository stepsRepository;

	@Autowired
	private WfAuthRoleRepository authRoleRepository;

	@Autowired
	private WfStepsActionsRepository wfStepsActionsRepository;

	@Override
	public WfResponse<?> createBusinessProcess(BusinessProcessDto businessProcessDto) {
		WfResponse<String> wfResponse = new WfResponse<String>();
		WfBusinessProcessEntity entity = businessProcessRepository.findTop1ByNameAndModuleNameAndIsActiveOrderByIdDesc(
				businessProcessDto.getName(), businessProcessDto.getModuleName(), true);
		if (entity != null && businessProcessDto.getId() == null) {
			return wfResponse;
		}

		WfBusinessProcessEntity businessProcessEntity = new WfBusinessProcessEntity();
		BeanUtils.copyProperties(businessProcessDto, businessProcessEntity);
		businessProcessEntity.setCreatedDate(new Date());

		WfModuleEntity moduleEntity = moduleRepository.findByIdAndIsActive(businessProcessDto.getModuleId(), true);
		businessProcessEntity.setModuleName(moduleEntity.getName());

		businessProcessEntity = businessProcessRepository.save(businessProcessEntity);

		// if (businessProcessDto.getId() == null) {
		businessProcessDto.setId(businessProcessEntity.getId());
		businessProcessDto.setModuleId(moduleEntity.getId());
		businessProcessDto.setModuleName(moduleEntity.getName());
		businessProcessDto.setCreatedDate(
				DateUtils.dateToStringFormatYyyyMmDdHhMmSsSlash(businessProcessEntity.getCreatedDate()));

		createBusinessProcessClass(businessProcessDto);
		// }
		wfResponse.setAckNo(String.valueOf(businessProcessEntity.getId()));

		return wfResponse;

	}

	@Override
	public WfResponse<?> getBusinessProcesses() {
		WfResponse<List<WfBusinessProcessEntity>> wfResponse = new WfResponse<>();
		// List<BusinessProcessDto> businessProcessDtos = new
		// ArrayList<BusinessProcessDto>();
		List<WfBusinessProcessEntity> list = businessProcessRepository.findAll();
		wfResponse.setResponse(list);
		return wfResponse;
	}

	@Override
	public WfResponse<?> getBusinessProcesse(Long id) {
		WfResponse<WfBusinessProcessEntity> wfResponse = new WfResponse<>();
		// List<BusinessProcessDto> businessProcessDtos = new
		// ArrayList<BusinessProcessDto>();
		Optional<WfBusinessProcessEntity> optional = businessProcessRepository.findById(id);
		WfBusinessProcessEntity entity = optional.isPresent() ? optional.get() : new WfBusinessProcessEntity();
		wfResponse.setResponse(entity);
		return wfResponse;
	}

	@Override
	public WfResponse<?> saveTask(ActionTransitionsDto actionTransitionsDto) {

		WfResponse<String> wfResponse = new WfResponse<>();
		try {
			Optional<WfBusinessProcessEntity> optional = businessProcessRepository
					.findById(actionTransitionsDto.getBpId());
			WfBusinessProcessEntity entity = optional.isPresent() ? optional.get() : null;
			if (entity != null) {
				WfActionTransitionsEntity actionTransitionsEntity = new WfActionTransitionsEntity();
				BeanUtils.copyProperties(actionTransitionsDto, actionTransitionsEntity);

				Optional<WfActionsEntity> optional2 = actionsRepository.findById(actionTransitionsDto.getActionId());
				WfActionsEntity actionEntity = optional2.isPresent() ? optional2.get() : null;

				actionTransitionsEntity.setActionCode(actionEntity.getCode());

				Optional<WfStepsEntity> optional3 = wfStepsRepository.findById(actionTransitionsDto.getCurrentStepId());
				Optional<WfStepsEntity> optional4 = wfStepsRepository.findById(actionTransitionsDto.getNextStepId());

				WfStepsEntity currentStep = optional3.isPresent() ? optional3.get() : null;
				WfStepsEntity nextStep = optional4.isPresent() ? optional4.get() : null;

				actionTransitionsEntity.setCurrentStepCode(currentStep.getDescription());
				actionTransitionsEntity.setNextStepCode(nextStep.getDescription());

				if (actionTransitionsDto.getId() == null) {
					actionTransitionsEntity.setCreatedBy(1l);
					actionTransitionsEntity.setCreatedDate(new Date());
				}

				actionTransitionsEntity.setActionsEntity(actionEntity);
				// actionTransitionsEntity.setIsActive(true);
				actionTransitionsEntity.setProcessType(actionTransitionsDto.getSubProcessType());
				actionTransitionsEntity.setModuleId(entity.getModuleId());
				actionTransitionsEntity.setBusinessProcessEntity(entity);

				WfRulesEntity rulesEntity = new WfRulesEntity();
				RulesDto rules = actionTransitionsDto.getRules();
				if (rules != null) {
					rulesEntity.setRuleDescription(actionTransitionsDto.getRules().getRuleDescription());
				}
				actionTransitionsEntity.setWfRulesEntities(Arrays.asList(rulesEntity));

				List<WfAuthRoleEntity> roleEntity = authRoleRepository
						.findByIdInAndIsActive(actionTransitionsDto.getRoles(), true);

				List<WfStepsActionsEntity> stepActionList = actionTransitionsEntity.getId() != null
						? wfStepsActionsRepository.findByActionTransitionsEntityId(actionTransitionsEntity.getId())
						: null;

				if (stepActionList != null && stepActionList.size() > 0) {
					stepActionList.forEach(f -> f.setIsActive(false));
					wfStepsActionsRepository.saveAll(stepActionList);
				}
				stepActionList = new ArrayList<WfStepsActionsEntity>();

				for (WfAuthRoleEntity wfAuthRoleEntity : roleEntity) {
					WfStepsActionsEntity stepActionEntity = new WfStepsActionsEntity();
					WfStepsEntity stepsEntity = stepsRepository
							.findByIdAndIsActive(actionTransitionsDto.getNextStepId(), true);
					WfActionsEntity actionsEntity = actionsRepository
							.findByIdAndIsActive(actionTransitionsDto.getActionId(), true);
					stepActionEntity.setStepsEntity(stepsEntity);
					stepActionEntity.setActionsEntity(actionsEntity);
					stepActionEntity.setRoleId(wfAuthRoleEntity.getModuleRoleId());
					stepActionEntity.setIsActive(actionTransitionsDto.getIsActive());
					stepActionEntity.setActionTransitionsEntity(actionTransitionsEntity);
					stepActionList.add(stepActionEntity);
				}

				actionTransitionsEntity.setStepsActionsEntities(stepActionList);

				actionTransitionsEntity = wfActionTransitionsRepository.save(actionTransitionsEntity);
				wfResponse.setAckNo(String.valueOf(entity.getId()));

				// stepActionList = wfStepsActionsRepository.saveAll(stepActionList);

				wfResponse.setApplicationError(CommonUtils.response("0", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return wfResponse;
	}

	@Override
	public List<ActionTransitionsDto> getActionTrans(WfBusinessProcessEntity businessProcessEntity) {
		List<ActionTransitionsDto> list = new ArrayList<ActionTransitionsDto>();
		List<WfActionTransitionsEntity> wfActionTransitionsEntities = businessProcessEntity
				.getWfActionTransitionsEntities();
		if (wfActionTransitionsEntities != null && wfActionTransitionsEntities.size() > 0) {
			for (WfActionTransitionsEntity actionTransitionsEntity : wfActionTransitionsEntities) {
				ActionTransitionsDto transitionsDto = new ActionTransitionsDto();
				BeanUtils.copyProperties(actionTransitionsEntity, transitionsDto);
				WfActionsEntity actionsEntity = actionTransitionsEntity.getActionsEntity();
				transitionsDto.setActionId(actionsEntity.getId());
				transitionsDto.setSubProcessType(actionTransitionsEntity.getProcessType());
				List<WfStepsActionsEntity> stepsActionsEntities = actionTransitionsEntity.getStepsActionsEntities();

				List<WfAuthRoleEntity> rolesList = authRoleRepository.findByModuleRoleIdInAndIsActive(
						stepsActionsEntities.stream().map(WfStepsActionsEntity::getRoleId).collect(Collectors.toList()),
						true);

				List<String> roleNames = rolesList.stream().map(WfAuthRoleEntity::getModuleRoleName)
						.collect(Collectors.toList());
				transitionsDto.setRoleNames(roleNames);
				transitionsDto.setActionCode(actionsEntity.getDisplayName());
				list.add(transitionsDto);
			}
		}
		return list;

	}

	@Override
	public WfResponse<?> getBusinessProcessByModuleId(Long modId) {
		WfResponse<List<BusinessProcessDto>> wfResponse = new WfResponse<>();
		// List<BusinessProcessDto> businessProcessDtos = new
		// ArrayList<BusinessProcessDto>();
		List<WfBusinessProcessEntity> list = businessProcessRepository.findByModuleIdAndIsActive(modId, true);
		List<BusinessProcessDto> businessProcessDtos = new ArrayList<BusinessProcessDto>();
		for (WfBusinessProcessEntity wfBusinessProcessEntity : list) {
			BusinessProcessDto dto = new BusinessProcessDto();
			BeanUtils.copyProperties(wfBusinessProcessEntity, dto);
			businessProcessDtos.add(dto);
		}

		wfResponse.setResponse(businessProcessDtos);
		return wfResponse;
	}

}
