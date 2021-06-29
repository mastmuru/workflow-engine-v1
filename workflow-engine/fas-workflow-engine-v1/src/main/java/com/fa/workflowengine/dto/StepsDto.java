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
public class StepsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private ModuleDto processId;
	private Long statusId;
	private String description;
	private Boolean isActive = true;
	private Long roleId;
	private Date createdDate;
	private Long createdBy;
	private String statusName;
	private String moduleName;
	private Long moduleId;

}
