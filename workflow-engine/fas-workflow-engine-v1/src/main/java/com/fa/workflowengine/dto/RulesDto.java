/**
 * 
 */
package com.fa.workflowengine.dto;

import java.io.Serializable;
import java.util.Date;

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
public class RulesDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private Double min;
	private Double max;
	private String result;
	private String lineOfBusiness;
	private String claimType;
	private Boolean isActive = true;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;
	private String ruleDescription;
}
