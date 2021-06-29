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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.dto.AuthRoleDto;
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
@RestController
@RequestMapping({ "/auth-role" })
public class AuthRoleController {

	@Autowired
	@Qualifier("authRoleService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	WfServiceImpl serviceImpl;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	public static final String TITLE = "Authority Role";

	public static List<String> links = Arrays.asList("/auth-role");

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
		model.setViewName("components/auth-role/list");
		return model;
	}

	@GetMapping("/form")
	public ModelAndView savePage() {
		AuthRoleDto entity = new AuthRoleDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);
		modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.setViewName("components/auth-role/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@ModelAttribute("formObject") AuthRoleDto model) {
		WfRequest<AuthRoleDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/auth-role");
	}

	@GetMapping("/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/auth-role/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.addObject("modules", moduleService.getByIsActive());
		model.setViewName("components/auth-role/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}	

	@SuppressWarnings("unchecked")
	@GetMapping("/getAuthRolesByModuleId/{id}")
	public List<AuthRoleDto> getAuthRolesByModuleId(@PathVariable("id") Long id) {
		WfResponse<?> response = (WfResponse<?>) service.getByModuleId(id);
		return (List<AuthRoleDto>) response.getResponse();
	}

}
