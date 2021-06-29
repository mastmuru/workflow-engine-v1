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

import com.fa.workflowengine.dto.StepsDto;
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
public class StepsController {

	@Autowired
	@Qualifier("stepService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	@Qualifier("statusService")
	private WfServiceIntf<?, ?> statusService;

	@Autowired
	WfServiceImpl serviceImpl;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	public static final String TITLE = "Tasks";
	public static List<String> links = Arrays.asList("/tasks");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/tasks")
	public ModelAndView process() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());

		model.addObject("title", TITLE);
		model.setViewName("components/tasks/list");
		return model;
	}

	@GetMapping("/tasks/form")
	public ModelAndView savePage() {
		StepsDto entity = new StepsDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);

		WfResponse<?> response = (WfResponse<?>) statusService.getByIsActive();
		modelAndView.addObject("statusList", response.getResponse());

		modelAndView.addObject("modules", moduleService.getByIsActive());

		modelAndView.setViewName("components/tasks/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	// add Course

	@PostMapping("/tasks/save")
	public ModelAndView save(@ModelAttribute("formObject") StepsDto model) {
		WfRequest<StepsDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/tasks");
	}

	@GetMapping("/tasks/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());

		WfResponse<?> response1 = (WfResponse<?>) statusService.getByIsActive();
		model.addObject("statusList", response1.getResponse());

		model.setViewName("components/tasks/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/tasks/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());

		WfResponse<?> response1 = (WfResponse<?>) statusService.getByIsActive();
		model.addObject("statusList", response1.getResponse());

		model.addObject("modules", moduleService.getByIsActive());

		model.setViewName("components/tasks/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

}
