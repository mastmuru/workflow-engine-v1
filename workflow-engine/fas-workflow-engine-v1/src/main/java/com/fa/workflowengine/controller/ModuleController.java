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

import com.fa.workflowengine.dto.ModuleDto;
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
public class ModuleController {

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> service;

	@Autowired
	WfServiceImpl serviceImpl;

	public static final String TITLE = "Module";

	public static List<String> links = Arrays.asList("/module");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/module")
	public ModelAndView process() {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getAll();

		model.addObject("list", response.getResponse());
		model.addObject("title", TITLE);
		model.setViewName("components/module/list");
		return model;
	}

	@GetMapping("/module/form")
	public ModelAndView savePage() {
		ModuleDto entity = new ModuleDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);
		modelAndView.setViewName("components/module/form");
		modelAndView.addObject("title", TITLE);
		CommonUtils.setLinks(modelAndView, links);
		return modelAndView;
	}

	// add Course

	@PostMapping("/module/save")
	public ModelAndView save(@ModelAttribute("formObject") ModuleDto model) {
		WfRequest<ModuleDto> request = new WfRequest<>();
		request.setRequest(model);
		Object save = service.save(request);
		return new ModelAndView("redirect:/module");
	}

	@GetMapping("/module/view/{id}")
	public ModelAndView view(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/module/view");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

	@GetMapping("/module/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView();
		WfResponse<?> response = (WfResponse<?>) service.getById(id);
		model.addObject("formObject", response.getResponse());
		model.setViewName("components/module/form");
		model.addObject("title", TITLE);
		CommonUtils.setLinks(model, links);
		return model;
	}

}
