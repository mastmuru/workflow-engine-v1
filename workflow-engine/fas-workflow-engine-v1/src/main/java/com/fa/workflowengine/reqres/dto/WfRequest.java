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
public class WfRequest<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ackNo;
	private String actionCode;
	private T request;
}
