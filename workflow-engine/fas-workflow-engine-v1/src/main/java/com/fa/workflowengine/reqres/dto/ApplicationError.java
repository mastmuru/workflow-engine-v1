/**
 * 
 */
package com.fa.workflowengine.reqres.dto;

import java.io.Serializable;

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
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorDescription;

}
