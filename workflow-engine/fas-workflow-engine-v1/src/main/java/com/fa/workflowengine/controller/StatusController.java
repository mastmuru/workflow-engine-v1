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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.dto.StatusDto;
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
//statusService
@Controller
@RestController
public class StatusController {

	@Autowired
	@Qualifier("statusService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	WfServiceImpl serviceImpl;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	public static final String TITLE = "Process Status";
	public static List<String> links = Arrays.asList("/status");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/status")
	public ModelAndView process() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/status/list");
		return model;
	}

	@GetMapping("/status/form")
	public ModelAndView savePage() {
		StatusDto entity = new StatusDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);
		modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.setViewName("components/status/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	// add Course

	@PostMapping("/status/save")
	public ModelAndView save(@ModelAttribute("formObject") StatusDto model) {
		WfRequest<StatusDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/status");
	}

	@GetMapping("/status/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/status/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/status/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.addObject("modules", moduleService.getByIsActive());
		model.setViewName("components/status/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/status/getStatusByModuleId/{id}")
	public List<StatusDto> getStatusByModuleId(@PathVariable("id") Long id) {
		WfResponse<?> response = (WfResponse<?>) service.getByModuleId(id);
		return (List<StatusDto>) response.getResponse();
	}

}
