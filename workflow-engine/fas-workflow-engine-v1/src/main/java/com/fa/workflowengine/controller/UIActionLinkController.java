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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.dto.UIActionLinkDto;
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
public class UIActionLinkController {

	@Autowired
	@Qualifier("uiActionLinkService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	@Qualifier("actionService")
	private WfServiceIntf<?, ?> actionService;

	@Autowired
	@Qualifier("authRoleService")
	private WfServiceIntf<?, ?> authRoleService;

	@Autowired
	@Qualifier("statusService")
	private WfServiceIntf<?, ?> statusService;

	@Autowired
	@Qualifier("pageService")
	private WfServiceIntf<?, ?> pageService;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	@Autowired
	WfServiceImpl serviceImpl;

	public static final String TITLE = "UI Action/Link";

	public static List<String> links = Arrays.asList("/ui-action-link");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/ui-action-link")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();
		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/ui-action-link/list");
		return model;
	}

	public void setModelObject(ModelAndView modelAndView) {
		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		modelAndView.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		modelAndView.addObject("authRoles", authRoles.getResponse());

		WfResponse<?> response = (WfResponse<?>) statusService.getByIsActive();
		modelAndView.addObject("statusList", response.getResponse());

		WfResponse<?> module = (WfResponse<?>) moduleService.getByIsActive();
		modelAndView.addObject("modules", module.getResponse());

		WfResponse<?> page = (WfResponse<?>) pageService.getByIsActive();
		modelAndView.addObject("pages", page.getResponse());

	}

	@GetMapping("/ui-action-link/form")
	public ModelAndView saveUIActionLinkPage() {
		UIActionLinkDto entity = new UIActionLinkDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);

		setModelObject(modelAndView);
		CommonUtils.setLinks(modelAndView, links);

		modelAndView.setViewName("components/ui-action-link/form");
		modelAndView.addObject("title", TITLE);
		return modelAndView;
	}

	// add Course

	@PostMapping("/ui-action-link/save")
	public ModelAndView saveUIActionLink(@ModelAttribute("formObject") UIActionLinkDto model) {
		WfRequest<UIActionLinkDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/ui-action-link");
	}

	@PostMapping("/ui-action-link/api/saveActionLink")
	public WfResponse<?> saveActionLink(@RequestBody UIActionLinkDto model) {
		WfRequest<UIActionLinkDto> request = new WfRequest<>();
		request.setRequest(model);
		WfResponse<?> save = (WfResponse<?>) service.save(request);
		return save;
	}

	@GetMapping("/ui-action-link/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/ui-action-link/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);

		return model;
	}

	@GetMapping("/ui-action-link/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		setModelObject(model);
		model.setViewName("components/ui-action-link/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/ui-action-link/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		setModelObject(model);
		model.setViewName("components/ui-action-link/delete");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@PostMapping("/ui-action-link/delete")
	public ModelAndView delete(@ModelAttribute("formObject") UIActionLinkDto model) {
		WfRequest<UIActionLinkDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.deleteById(model.getId());
		return new ModelAndView("redirect:/ui-action-link");
	}

}
