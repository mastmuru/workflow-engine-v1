/**
 * 
 */
package com.fa.workflowengine.master.service;

import com.fa.workflowengine.master.entity.UserMstEntity;

/**
 * @author Muruganandam
 *
 */
public interface UserService {
	void save(UserMstEntity user);

	UserMstEntity findByUsername(String username);
}
