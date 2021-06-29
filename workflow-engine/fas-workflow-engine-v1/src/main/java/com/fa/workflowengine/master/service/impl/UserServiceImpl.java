/**
 * 
 */
package com.fa.workflowengine.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.master.entity.UserMstEntity;
import com.fa.workflowengine.master.repository.RoleMstRepository;
import com.fa.workflowengine.master.repository.UserMstRepository;
import com.fa.workflowengine.master.service.UserService;

/**
 * @author Muruganandam
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMstRepository userRepository;
	@Autowired
	private RoleMstRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(UserMstEntity user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles((roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public UserMstEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
