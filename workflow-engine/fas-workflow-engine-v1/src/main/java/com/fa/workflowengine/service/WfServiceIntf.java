/**
 * 
 */
package com.fa.workflowengine.service;

import com.fa.workflowengine.reqres.dto.WfRequest;

/**
 * @author Muruganandam, FA Softwares
 *
 */
public interface WfServiceIntf<T1, T2> {
	T2 save(WfRequest<?> request);

	T2 getAll();

	T2 getById(Long id);

	T2 getByIsActive();

	T2 deleteById(Long id);

	T2 getByModuleId(Long id);

}
