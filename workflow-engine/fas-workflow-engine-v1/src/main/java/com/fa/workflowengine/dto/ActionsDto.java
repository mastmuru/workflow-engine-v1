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
public class ActionsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private String displayName;
	private Boolean isActive = true;
	private Date createdDate;
	private Long createdBy;
	private String moduleName;
	private Long moduleId;

}
