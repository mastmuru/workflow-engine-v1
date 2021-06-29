/**
 * 
 */
package com.fa.workflowengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;
import com.fa.workflowengine.service.BusinessProcessService;

/**
 * @author Muruganandam, FA Softwares
 *
 */
//@Controller
public class WfController {

	@Autowired
	BusinessProcessService service;

	@Autowired
	WfServiceImpl serviceImpl;

	@GetMapping({ "/home" })
	public ModelAndView welcomePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("menus", serviceImpl.getActiveMenu());
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("menus", serviceImpl.getActiveMenu());
		modelAndView.setViewName("about");
		return modelAndView;
	}

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}
}
