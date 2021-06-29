/**
 * 
 */
package com.fa.workflowengine.dto;

import java.io.Serializable;

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
public class UIActionLinkDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long wfStatusId;
	private Long wfRoleId;

	private Long status;
	private String statusName;
	private String displayName;
	private Long actionId;
	private String actionName;
	private Boolean isEnable = true;
	private Long roleId;
	private String roleName;
	private String pageCode;
	private String attribute = "btn btn-default";
	private String type;
	private String createdDate;
	private String code;
	private String orgType;
	private Boolean isActive = true;
	private Integer orderNo;
	private Long moduleId;
	private String moduleName;
	private Long bpId;
	private String bpName;
}
