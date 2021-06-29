/**
 * 
 */
package com.fa.workflowengine.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.dto.UIPageDto;
import com.fa.workflowengine.entity.UIPageEntity;
import com.fa.workflowengine.repository.UIPageRepository;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;


/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service("pageService")
public class PageServiceImpl implements WfServiceIntf<WfRequest<?>, WfResponse<?>> {

	@Autowired
	private UIPageRepository repository;

	@Override
	public WfResponse<?> save(WfRequest<?> t1) {
		WfResponse<?> response = new WfResponse<>();
		UIPageDto dto = (UIPageDto) t1.getRequest();
		UIPageEntity entity = new UIPageEntity();
		BeanUtils.copyProperties(dto, entity);
		repository.save(entity);
		return response;
	}

	@Override
	public WfResponse<?> getAll() {
		WfResponse<List<UIPageEntity>> response = new WfResponse<>();
		response.setResponse(repository.findAll());
		return response;
	}

	@Override
	public WfResponse<?> getById(Long id) {
		WfResponse<UIPageEntity> response = new WfResponse<>();
		Optional<UIPageEntity> optional = repository.findById(id);
		response.setResponse(optional.isPresent() ? optional.get() : null);
		return response;
	}

	@Override
	public WfResponse<?> getByIsActive() {
		WfResponse<List<UIPageEntity>> response = new WfResponse<>();
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
