/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.ModuleDto;
import com.fa.workflowengine.entity.WfModuleEntity;
import com.fa.workflowengine.repository.WfModuleRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;


/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("moduleService")
public class ModuleServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private WfModuleRepository repository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		ModuleDto dto = (ModuleDto) t1.getRequest();
		WfModuleEntity entity = new WfModuleEntity();
		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<WfModuleEntity>> response = new WfResponse<>();
		response.setResponse(repository.findAll());
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<WfModuleEntity> response = new WfResponse<>();
		Optional<WfModuleEntity> optional = repository.findById(id);
		response.setResponse(optional.isPresent() ? optional.get() : null);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<WfModuleEntity>> response = new WfResponse<>();
		response.setResponse(repository.findByIsActive(true));
		return response;
	}

	@Override
	public WfResponse<?> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfResponse<?> getByModuleId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
