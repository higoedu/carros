package com.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carros.domain.User;
import com.carros.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//return null;
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user = userRepository.findByLogin(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("user not found");			
		}
		
		//return User.withUsername(username).password(user.getSenha()).roles("USER").build();
		
		/*
		if(username.equals("user")) {
			return User.withUsername(username).password(encoder.encode("user")).roles("USER").build();
		} else if(username.equals("admin")) {
			return User.withUsername(username).password(encoder.encode("admin")).roles("USER", "ADMIN").build();
		}
		
		throw new UsernameNotFoundException("user not found");
		*/
		
		return user;
	}
}
