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

import com.fa.workflowengine.dto.ActionTransitionsDto;
import com.fa.workflowengine.dto.StatusDto;
import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.WfServiceIntf;
import com.fa.workflowengine.utils.CommonUtils;
import com.fa.workflowengine.utils.Constants;

/**
 * @author Muruganandam, FA Softwares
 *
 */

@Controller
public class ActionTransitionController {
	@Autowired
	@Qualifier("actionTransService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	@Qualifier("stepService")
	private WfServiceIntf<?, ?> serviceIntf;

	@Autowired
	@Qualifier("actionService")
	private WfServiceIntf<?, ?> actionService;

	@Autowired
	WfServiceImpl serviceImpl;

	public static final String TITLE = "Business Process Action Transition";
	public static List<String> links = Arrays.asList("/action-transition");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/action-transition")
	public ModelAndView process() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/action-transition/list");
		return model;
	}

	@GetMapping("/action-transition/form")
	public ModelAndView savePage() {
		StatusDto entity = new StatusDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);
		modelAndView.setViewName("components/action-transition/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	// add Course

	@PostMapping("/action-transition/save")
	public ModelAndView save(@ModelAttribute("formObject") ActionTransitionsDto model) {
		WfRequest<ActionTransitionsDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/action-transition");
	}

	@GetMapping("/action-transition/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/action-transition/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/action-transition/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();

		WfResponse<ActionTransitionsDto> wfResponse = (WfResponse<ActionTransitionsDto>) service.getById(id);
		ActionTransitionsDto response2 = wfResponse.getResponse();
		modelAndView.addObject("formObject", response2);

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByIsActive();
		modelAndView.addObject("currentTasks", response.getResponse());
		modelAndView.addObject("nextTasks", response.getResponse());
		modelAndView.addObject("actions", Constants.getActionList());
		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());

		modelAndView.setViewName("components/action-transition/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

}
