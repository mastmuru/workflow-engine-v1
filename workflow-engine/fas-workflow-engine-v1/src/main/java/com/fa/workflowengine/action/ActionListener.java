/**
 * 
 */
package com.fa.workflowengine.action;

import com.fa.workflowengine.reqres.dto.WfRequest;
import com.fa.workflowengine.reqres.dto.WfResponse;

/**
 * @author Muruganandam, FA Softwares
 *
 */
public interface ActionListener {
	WfResponse<?> action(WfRequest<?> request);
}
