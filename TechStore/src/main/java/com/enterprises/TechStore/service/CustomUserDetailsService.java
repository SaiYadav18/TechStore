//
//package com.enterprises.TechStore.service;
//
//import com.enterprises.TechStore.entity.User;
//import com.enterprises.TechStore.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userRepository.findByEmail(username);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("User Not Found");
//		}
//
//		return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
//				.password(user.getPassword()).roles(user.getRole()).build();
//	}
//}
