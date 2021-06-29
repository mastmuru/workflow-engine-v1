/**
 * 
 */
package com.fa.workflowengine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.entity.WfQueryEntity;
import com.fa.workflowengine.repository.WfQueryRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.QueryServiceIntf;
import com.fa.workflowengine.service.WfServiceIntf;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("queryService")
public class QueryServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>>, QueryServiceIntf {

	@Autowired
	private WfQueryRepository repository;

	@Override
	public WfResponse<?> save(WfRequest<?> request) {
		WfResponse<WfQueryEntity> response = new WfResponse<>();
		WfQueryEntity queryEntity = (WfQueryEntity) request.getRequest();
		queryEntity.setQuery(queryEntity.getQuery().trim());
		repository.save(queryEntity);
		response.setResponse(queryEntity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<WfQueryEntity> response = new WfResponse<>();
		WfQueryEntity queryEntity = repository.findByIdAndIsActive(id, true);
		response.setResponse(queryEntity);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfResponse<?> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WfQueryEntity getByCode(String code) {
		WfQueryEntity queryEntity = repository.findByCodeAndIsActive(code, true);
		return queryEntity;
	}

	@Override
	public WfResponse<?> getByModuleId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
