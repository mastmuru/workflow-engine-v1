package com.fa.workflowengine.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fa.workflowengine.dto.ActionTransitionsDto;
import com.fa.workflowengine.dto.BusinessProcessDto;
import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.entity.WfBusinessProcessEntity;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.BusinessProcessService;
import com.fa.workflowengine.service.WfServiceIntf;
import com.fa.workflowengine.utils.Constants;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Controller
@RestController
@RequestMapping({ "/bp/" })
public class BusinessProcessController {
	@Autowired
	BusinessProcessService service;

	@Autowired
	@Qualifier("stepService")
	private WfServiceIntf<?, ?> serviceIntf;

	@Autowired
	@Qualifier("actionService")
	private WfServiceIntf<?, ?> actionService;

	@Autowired
	@Qualifier("actionTransService")
	private WfServiceIntf<?, ?> actionTransService;

	@Autowired
	@Qualifier("moduleService")
	private WfServiceIntf<?, ?> moduleService;

	@Autowired
	@Qualifier("authRoleService")
	private WfServiceIntf<?, ?> authRoleService;

	@Autowired
	WfServiceImpl serviceImpl;

	public static List<String> links = Arrays.asList("/bp/getBusinessProcesses");
	public static List<String> sublinks = Arrays.asList("/bp/getBusinessProcesses", "/bp/viewBusinessProcess");
	public static String backSym = "< ";

	public void setLinks(ModelAndView model) {
		model.addObject("links", links);
	}

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	// getBusinessProcesses
	@GetMapping({ "/getBusinessProcesses", "" })
	public ModelAndView getBusinessProcesses() {
		ModelAndView modelAndView = new ModelAndView();
		WfResponse<?> businessProcesses = service.getBusinessProcesses();
		modelAndView.addObject("businessProcessOb", businessProcesses.getResponse());
		modelAndView.setViewName("components/businessProcess/businessProcessList");
		modelAndView.addObject("title", "Business Process");
		return modelAndView;
	}

	@GetMapping("/createBusinessProcessPage")
	public ModelAndView createBusinessProcessPage() {
		BusinessProcessDto course = new BusinessProcessDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());
		modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.addObject("businessProcessOb", course);
		modelAndView.setViewName("components/businessProcess/businessProcessForm");
		modelAndView.addObject("title", "Business Process");
		setLinks(modelAndView);
		return modelAndView;
	}

	@PostMapping("/saveBusinessProcess")
	public ModelAndView createBusinessProcess(
			@ModelAttribute("businessProcessForm") BusinessProcessDto businessProcessDto) {
		WfResponse<?> businessProcess = service.createBusinessProcess(businessProcessDto);
		return new ModelAndView("redirect:/bp/viewBusinessProcess/" + businessProcess.getAckNo());
	}

	// Delete List

	@GetMapping("/viewBusinessProcess/{id}")
	public ModelAndView viewBusinessProcess(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		ActionTransitionsDto dto = new ActionTransitionsDto();
		WfBusinessProcessEntity businessProcessEntity = (WfBusinessProcessEntity) service.getBusinessProcesse(id)
				.getResponse();
		modelAndView.addObject("businessProcessOb", businessProcessEntity);
		dto.setBpId(businessProcessEntity.getId());
		dto.setSubProcessType(businessProcessEntity.getModuleName());
		setLinks(modelAndView);
		modelAndView.addObject("wfActionTrn", dto);

		List<ActionTransitionsDto> actionTrans = service.getActionTrans(businessProcessEntity);
		modelAndView.addObject("businessTasks", actionTrans);

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByModuleId(businessProcessEntity.getModuleId());
		modelAndView.addObject("currentTasks", response.getResponse());
		modelAndView.addObject("nextTasks", response.getResponse());

		// modelAndView.addObject("actionKeyValue",
		// actionService.getByModuleId(businessProcessEntity.getModuleId()));

		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());

		modelAndView.setViewName("components/businessProcess/viewbusinessProcess");

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByModuleId(businessProcessEntity.getModuleId());
		modelAndView.addObject("authRoles", authRoles.getResponse());

		modelAndView.addObject("title", "Business Process");
		return modelAndView;
	}

	// getBusinessProcesses
	@GetMapping("/viewbusinessProcess")
	public ModelAndView viewbusinessProcess(Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("businessProcessOb", service.getBusinessProcesse(id).getResponse());
		modelAndView.setViewName("getBusinessProcesses");
		modelAndView.addObject("title", "Business Process");
		setLinks(modelAndView);
		return modelAndView;
	}

	// Update List

	@GetMapping("/updateBusinessProcess/{id}")
	public ModelAndView updateBusinessProcess(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("businessProcessOb", service.getBusinessProcesse(id).getResponse());
		modelAndView.addObject("actions", Constants.getActionList());
		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());
		modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.setViewName("components/businessProcess/businessProcessForm");
		modelAndView.addObject("title", "Business Process");
		setLinks(modelAndView);
		return modelAndView;
	}

	@GetMapping("/createTaskStep")
	public ModelAndView createTaskStep() {
		ActionTransitionsDto dto = new ActionTransitionsDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());
		modelAndView.addObject("modules", moduleService.getByIsActive());
		modelAndView.addObject("wfActionTrn", dto);
		modelAndView.setViewName("components/businessProcess/businessProcessForm");
		modelAndView.addObject("title", "Business Process");
		setLinks(modelAndView);
		return modelAndView;
	}

	@PostMapping("/saveTaskStep")
	public ModelAndView createTaskStep(@ModelAttribute("wfActionTrn") ActionTransitionsDto businessProcessDto) {
		// service.createBusinessProcess(businessProcessDto);
		System.out.println("saveTaskStep");
		ModelAndView modelAndView = new ModelAndView();
		WfResponse<?> saveTask = service.saveTask(businessProcessDto);

		modelAndView.addObject("responseData", saveTask);
		// setLinks(modelAndView);
		modelAndView.setViewName("redirect:/bp/viewBusinessProcess/" + saveTask.getAckNo());

		return modelAndView;
	}

	// viewbusinessProcessTxnAction
	@GetMapping("/updateBusinessProcessTxnAction/{id}")
	public ModelAndView viewbusinessProcessTxnAction(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();

		WfResponse<ActionTransitionsDto> wfResponse = (WfResponse<ActionTransitionsDto>) actionTransService.getById(id);
		ActionTransitionsDto response2 = wfResponse.getResponse();
		modelAndView.addObject("businessProcessOb", response2.getBusinessProcessDto());
		modelAndView.addObject("wfActionTrn", response2);

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByModuleId(response2.getModuleId());
		modelAndView.addObject("currentTasks", response.getResponse());
		modelAndView.addObject("nextTasks", response.getResponse());
		modelAndView.addObject("actions", Constants.getActionList());

		// modelAndView.addObject("actionKeyValue",
		// actionService.getByModuleId(response2.getModuleId()));
		modelAndView.addObject("actionKeyValue", actionService.getByIsActive());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByModuleId(response2.getModuleId());
		modelAndView.addObject("authRoles", authRoles.getResponse());

		modelAndView.setViewName("components/businessProcess/viewbusinessProcessTxnAction");
		modelAndView.addObject("title", "Business Process");
		// setLinks(modelAndView);

		Map<String, String> map = new HashMap<String, String>();
		map.put("/bp/getBusinessProcesses", backSym + "Home");
		map.put("/bp/viewBusinessProcess/" + response2.getBpId(), backSym + "Back");
		modelAndView.addObject("links", map);
		return modelAndView;
	}

	// getBusinessProcessByModuleId
	@SuppressWarnings("unchecked")
	@GetMapping("/getBusinessProcessByModuleId/{id}")
	public List<BusinessProcessDto> getBusinessProcessByModuleId(@PathVariable("id") Long id) {
		WfResponse<?> response = (WfResponse<?>) service.getBusinessProcessByModuleId(id);
		return (List<BusinessProcessDto>) response.getResponse();
	}
}
