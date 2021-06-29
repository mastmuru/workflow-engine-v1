/**
 * 
 */
package com.fa.workflowengine.master.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.repository.MenuRepository;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Service
public class WfServiceImpl {
	@Autowired
	private MenuRepository repository;

	public List<UIMenuEntity> getAllMenu() {
		return repository.findAll();
	}

	public List<UIMenuEntity> getActiveMenu() {
		return repository.findByIsActiveOrderByOrderNoAsc(true);
	}
}
