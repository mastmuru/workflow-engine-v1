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
public class TransitionActivitiesDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  Long id;
	private  Long transitionActivitiesId;
	private  Long activtiesId;
	private  Long templateId;
	private  Long createdBy;
	private  Date createdDate;

}
