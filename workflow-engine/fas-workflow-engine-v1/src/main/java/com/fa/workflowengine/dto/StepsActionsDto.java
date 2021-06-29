/**
 * 
 */
package com.fa.workflowengine.dto;

import java.io.Serializable;
import java.util.Date;

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
public class StepsActionsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private StepsDto steps;
	private ActionsDto action;
	private Long createdBy;
	private Date createdDate;
	private Long stepId;
	private Long actionId;
	private Long roleId;
	private String actionName;
	private String roleName;
	private String stepName;
	private Boolean isActive = true;

}
