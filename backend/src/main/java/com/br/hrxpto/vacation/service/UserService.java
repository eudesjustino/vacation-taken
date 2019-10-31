package com.br.hrxpto.vacation.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.br.hrxpto.vacation.exception.ExistingCredentialsException;
import com.br.hrxpto.vacation.model.User;

public interface UserService extends UserDetailsService{
	
	User save(User user) throws ExistingCredentialsException;
	Optional<User> findUser(String email);
	
}
