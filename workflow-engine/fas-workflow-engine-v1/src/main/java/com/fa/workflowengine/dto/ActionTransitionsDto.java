/**
 * 
 */
package com.fa.workflowengine.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActionTransitionsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long actionId;
	private String actionCode;
	private Long currentStepId;
	private String currentStepCode;
	private Long nextStepId;
	private String nextStepCode;
	private Long rule_id;
	private String rule_result;
	private Boolean isActive = true;
	private Long createdBy;
	private Date createdDate;
	private String message;
	private String subProcessType;
	private Long bpId;
	private Long moduleId;
	private String bpName;
	private BusinessProcessDto businessProcessDto;
	private RulesDto rules;
	private String ruleDescription;
	private Long roleId;
	private List<Long> roles;
	private List<String> roleNames;

}
