/**
 * 
 */
package com.fa.workflowengine.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.master.entity.UserMstEntity;
import com.fa.workflowengine.master.service.SecurityService;
import com.fa.workflowengine.master.service.UserService;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Controller
@RequestMapping({ "/wf/", "/" })
public class MasterController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	WfServiceImpl serviceImpl;

	@GetMapping("/registration")
	public String registration(Model model) {
		if (securityService.isAuthenticated()) {
			return "redirect:/";
		}

		model.addAttribute("userForm", new UserMstEntity());

		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") UserMstEntity userForm, BindingResult bindingResult) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (securityService.isAuthenticated()) {
			return "redirect:/";
		}

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			// model.addAttribute("message", "Logged out successfully.");
			model.addAttribute("message", "");

		return "login";
	}

	@GetMapping({ "/", "/home", "/welcome" })
	public ModelAndView welcome(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("menus", serviceImpl.getActiveMenu());
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.setViewName("about");
		return modelAndView;
	}

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}
}
