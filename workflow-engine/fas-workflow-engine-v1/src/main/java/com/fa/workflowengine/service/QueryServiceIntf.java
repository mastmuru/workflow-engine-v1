/**
 * 
 */
package com.fa.workflowengine.service;

import com.fa.workflowengine.entity.WfQueryEntity;

/**
 * @author Muruganandam
 *
 */
public interface QueryServiceIntf {
	WfQueryEntity getByCode(String code);
}
