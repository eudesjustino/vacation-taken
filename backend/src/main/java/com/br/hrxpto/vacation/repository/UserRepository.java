package com.br.hrxpto.vacation.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.hrxpto.vacation.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	Optional<User> findByUsername(String username);

	
}
