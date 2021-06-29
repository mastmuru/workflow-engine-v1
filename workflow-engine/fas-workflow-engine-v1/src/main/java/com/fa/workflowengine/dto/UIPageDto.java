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
public class UIPageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private String name;
	private Boolean isActive = true;
}
