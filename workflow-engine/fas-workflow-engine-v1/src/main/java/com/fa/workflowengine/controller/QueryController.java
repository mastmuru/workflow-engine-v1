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

import com.fa.workflowengine.dto.StatusDto;
import com.fa.workflowengine.dto.StepsActionsDto;
import com.fa.workflowengine.entity.UIMenuEntity;
import com.fa.workflowengine.entity.WfQueryEntity;
import com.fa.workflowengine.entity.WfStatusEntity;
import com.fa.workflowengine.master.service.impl.WfServiceImpl;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;
import com.fa.workflowengine.service.QueryServiceIntf;
import com.fa.workflowengine.service.WfServiceIntf;
import com.fa.workflowengine.utils.CommonUtils;

/**
 * @author Muruganandam, FA Softwares
 *
 */

@Controller
@RequestMapping({ "/query" })
public class QueryController {

	@Autowired
	@Qualifier("queryService")
	private WfServiceIntf<?, ?> queryService;

	@Autowired
	@Qualifier("queryService")
	private QueryServiceIntf queryServiceIntf;

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

	@Autowired
	@Qualifier("statusService")
	private WfServiceIntf<?, ?> statusService;

	@Autowired
	@Qualifier("pageService")
	private WfServiceIntf<?, ?> pageService;

	public static final String TITLE = "Dynamic SQL Query";
	public static List<String> links = Arrays.asList("/query");

	@ModelAttribute(name = "headerMenus")
	public List<UIMenuEntity> getAlphabet() {
		return serviceImpl.getActiveMenu();
	}

	@GetMapping("/ui-action-query")
	public ModelAndView uiActionQuery() {

		WfQueryEntity entity = queryServiceIntf.getByCode("UIACTIONLINK");

		if (entity == null) {
			entity = new WfQueryEntity();
		}
		entity.setCode("UIACTIONLINK");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);

		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		modelAndView.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByIsActive();
		modelAndView.addObject("nextTasks", response.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		modelAndView.addObject("authRoles", authRoles.getResponse());

		WfResponse<?> statusresponse = (WfResponse<?>) statusService.getByIsActive();
		modelAndView.addObject("statusList", statusresponse.getResponse());

		WfResponse<?> pageresponse = (WfResponse<?>) pageService.getByIsActive();
		modelAndView.addObject("pages", pageresponse.getResponse());

		modelAndView.setViewName("components/query/ui-action-query-form");
		modelAndView.addObject("title", "UI Action/Link Query");
		// CommonUtils.setLinks(modelAndView, links);

		return modelAndView;
	}

	// workflow-task-step

	@GetMapping("/workflow-task-step")
	public ModelAndView workflowTaskStep() {
		WfQueryEntity entity = queryServiceIntf.getByCode("WFTASKSTEP");

		if (entity == null) {
			entity = new WfQueryEntity();
		}
		entity.setCode("WFTASKSTEP");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", entity);

		WfResponse<?> response1 = (WfResponse<?>) actionService.getByIsActive();
		modelAndView.addObject("actionKeyValue", response1.getResponse());

		WfResponse<?> response = (WfResponse<?>) serviceIntf.getByIsActive();
		modelAndView.addObject("nextTasks", response.getResponse());

		WfResponse<?> authRoles = (WfResponse<?>) authRoleService.getByIsActive();
		modelAndView.addObject("authRoles", authRoles.getResponse());

		WfResponse<?> statusresponse = (WfResponse<?>) statusService.getByIsActive();
		modelAndView.addObject("statusList", statusresponse.getResponse());

		WfResponse<?> pageresponse = (WfResponse<?>) pageService.getByIsActive();
		modelAndView.addObject("pages", pageresponse.getResponse());

		modelAndView.setViewName("components/query/workflow-task-step");
		modelAndView.addObject("title", "Workflow Task Step Query");
		// CommonUtils.setLinks(modelAndView, links);

		return modelAndView;
	}

	@PostMapping("/saveUIAction")
	public ModelAndView save(@ModelAttribute("formObject") WfQueryEntity model) {
		WfRequest<WfQueryEntity> request = new WfRequest<>();
		request.setRequest(model);
		WfResponse<WfQueryEntity> wfResponse = (WfResponse<WfQueryEntity>) queryService.save(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", wfResponse.getResponse());
		modelAndView.setViewName("components/query/ui-action-query-form");
		modelAndView.addObject("title", "UI Action/Link Query");
		// return new ModelAndView("redirect:/query/ui-action-query");
		return modelAndView;
	}

	@PostMapping("/saveWorkflowStep")
	public ModelAndView saveWorkflowStep(@ModelAttribute("formObject") WfQueryEntity model) {
		WfRequest<WfQueryEntity> request = new WfRequest<>();
		request.setRequest(model);
		WfResponse<WfQueryEntity> wfResponse = (WfResponse<WfQueryEntity>) queryService.save(request);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("formObject", wfResponse.getResponse());
		modelAndView.addObject("title", "Workflow Task Step Query");
		modelAndView.setViewName("components/query/workflow-task-step");
		// return new ModelAndView("redirect:/query/ui-action-query");
		return modelAndView;
	}

}
