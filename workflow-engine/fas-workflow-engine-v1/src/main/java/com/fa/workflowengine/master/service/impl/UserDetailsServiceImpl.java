/**
 * 
 */
package com.fa.workflowengine.master.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fa.workflowengine.master.entity.RoleMstEntity;
import com.fa.workflowengine.master.entity.UserMstEntity;
import com.fa.workflowengine.master.repository.UserMstRepository;

/**
 * @author Muruganandam
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMstRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		UserMstEntity user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (RoleMstEntity role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}
}
