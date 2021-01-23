package com.upvotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upvotes.domain.User;
import com.upvotes.repositories.UserRepository;
import com.upvotes.security.CustomSecurityUser;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Invalid username and Password");
		return new CustomSecurityUser(user);
	}
	

}
