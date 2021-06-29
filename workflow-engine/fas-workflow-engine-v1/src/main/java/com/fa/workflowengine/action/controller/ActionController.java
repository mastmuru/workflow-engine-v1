/**
 * 
 */
package com.fa.workflowengine.action.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.workflowengine.action.ActionListener;
import com.fa.workflowengine.reqres.dto.WfRequest;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@RestController
@RequestMapping("/")
public class ActionController {
	private static final Logger log = LoggerFactory.getLogger(ActionController.class);

	@Autowired
	private ApplicationContext appContext;

	@PostMapping(value = "/action")
	public ResponseEntity<?> action(@RequestBody WfRequest wfRequest, HttpServletRequest httpServletRequest) {
		log.info(wfRequest.getActionCode());
		ActionListener actionListener = appContext.getBean(wfRequest.getActionCode().toUpperCase(),
				ActionListener.class);
		return ResponseEntity.ok().body(actionListener.action(wfRequest));
	}
}
