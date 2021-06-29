/**
 * 
 */
package com.fa.workflowengine.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.dto.ActionsDto;
import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;
import com.fa.workflowengine.utils.CommonUtils;

/**
 * @author Muruganandam, FA Softwares
 *
 */

@Controller
public class ActionsController {

	@Autowired
	@Qualifier("actionService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	@Autowired
	WfServiceImpl serviceImpl;

	public static final String TITLE = "Actions";
	public static List<String> links = Arrays.asList("/action-mst");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/action-mst")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/actions/list");
		return model;
	}

	@GetMapping("/action-mst/form")
	public ModelAndView saveActionPage() {
		ActionsDto entity = new ActionsDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);
		// modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.setViewName("components/actions/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	// add Course

	@PostMapping("/action-mst/save")
	public ModelAndView saveAction(@ModelAttribute("formObject") ActionsDto model) {
		WfRequest<ActionsDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/action-mst");
	}

	@GetMapping("/action-mst/view/{id}")
	public ModelAndView viewAction(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/actions/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/action-mst/update/{id}")
	public ModelAndView updateAction(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		// model.addObject("modules", moduleService.getByIsActive());
		model.setViewName("components/actions/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

}
