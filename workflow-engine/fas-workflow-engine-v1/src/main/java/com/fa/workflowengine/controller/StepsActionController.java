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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.dto.StepsActionsDto;
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
@RequestMapping({ "/step-action" })
public class StepsActionController {

	@Autowired
	@Qualifier("stepActionService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	@Qualifier("stepService")
	private WfServiceIntf<?, ?> serviceIntf;

	@Autowired
	@Qualifier("actionService")
	private WfServiceIntf<?, ?> actionService;

	@Autowired
	@Qualifier("authRoleService")
	private WfServiceIntf<?, ?> authRoleService;

	@Autowired
	WfServiceImpl serviceImpl;

	public static final String TITLE = "Action/Task Step/Authority Role Mapping";
	public static List<String> links = Arrays.asList("/step-action");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping({ "" })
	public ModelAndView process() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/step-action/list");
		return model;
	}

	@GetMapping("/form")
	public ModelAndView savePage() {
		StepsActionsDto entity = new StepsActionsDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);

		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		modelAndView.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByIsActive();
		modelAndView.addObject("nextTasks", response.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		modelAndView.addObject("authRoles", authRoles.getResponse());

		modelAndView.setViewName("components/step-action/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);

		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("formObject") StepsActionsDto model) {
		WfRequest<StepsActionsDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/step-action");
	}

	@GetMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();

		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		model.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> response2 = (WfResponse<?>) serviceIntf.getByIsActive();
		model.addObject("nextTasks", response2.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		model.addObject("authRoles", authRoles.getResponse());

		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/step-action/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();

		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		model.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> response2 = (WfResponse<?>) serviceIntf.getByIsActive();
		model.addObject("nextTasks", response2.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		model.addObject("authRoles", authRoles.getResponse());

		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/step-action/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

}
