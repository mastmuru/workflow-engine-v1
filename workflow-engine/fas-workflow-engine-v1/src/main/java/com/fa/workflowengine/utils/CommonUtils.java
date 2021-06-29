/**
 * 
 */
package com.fa.workflowengine.utils;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.fa.workflowengine.reqres.dto.ApplicationError;
import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;


/**
 * @author Muruganandam, FA Softwares
 *
 */
public class CommonUtils implements Constants {
	public static WfResponse response(WfRequest request) {
		WfResponse<String> response = new WfResponse<>();
		ApplicationError applicationError = new ApplicationError("0", null);
		response.setApplicationError(applicationError);
		response.setAckNo(request.getAckNo());
		response.setResponse(request.getActionCode());
		return response;
	}

	public static ApplicationError response(String errorCode, String errorDescription) {
		return new ApplicationError(errorCode, errorDescription);
	}

	public static void setLinks(ModelAndView model, List<String> links) {
		model.addObject("links", links);
	}

	public static void main(String[] args) {
	}

}
