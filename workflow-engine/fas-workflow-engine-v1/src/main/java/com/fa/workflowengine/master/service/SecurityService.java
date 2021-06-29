/**
 * 
 */
package com.fa.workflowengine.master.service;

/**
 * @author Muruganandam
 *
 */
public interface SecurityService {
	boolean isAuthenticated();

	void autoLogin(String username, String password);
}
