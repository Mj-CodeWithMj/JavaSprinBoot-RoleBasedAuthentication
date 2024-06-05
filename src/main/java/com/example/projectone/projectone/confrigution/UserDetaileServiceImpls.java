package com.example.projectone.projectone.confrigution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.projectone.projectone.models.User;
import com.example.projectone.projectone.repository.UserRepository;

@Service
public class UserDetaileServiceImpls implements UserDetailsService {
	
	@Autowired
	UserRepository urepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = urepo.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find such username :"+username);
		}
		return user;
	}

}
