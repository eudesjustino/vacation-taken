package com.br.hrxpto.vacation.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.hrxpto.vacation.model.User;
import com.br.hrxpto.vacation.repository.UserRepository;
import com.br.hrxpto.vacation.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public User save(User user) {
		
	  String keyCripto = bcryptEncoder.encode(user.getPassword());
	  user.setPassword(keyCripto);
	  return repository.save(user);
	}

	@Override
	public Optional<User> findUser(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<User> user = repository.findByUsername(username);
		 if(user.isEmpty()) {
			 throw new UsernameNotFoundException("User not found with username: " + username);
		 }
		 
		 return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
					new ArrayList<>());
	}

}
