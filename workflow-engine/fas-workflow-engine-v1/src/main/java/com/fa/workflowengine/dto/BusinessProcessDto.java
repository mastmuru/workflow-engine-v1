/**
 * 
 */
package com.fa.workflowengine.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Muruganandam, FA Softwares
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessProcessDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private Boolean isActive = true;
	private String createdDate;
	private Long createdBy;
	private String actionCode;
	private String moduleName;
	private Long moduleId;
	public List<ActionsDto> actions;

}
