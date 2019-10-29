package com.br.hrxpto.vacation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.model.Employee;

public interface EmployeeService {

	public Employee createUpdate(Employee employee) throws HiringException;

	public void delete(Employee employee);
	
	public List<Employee> findAll();
	
	public Page<Employee> findAllPageble(PageRequest pageble);
	
	public Optional<Employee> findById(Long id);

	public List<Employee> employeesRequestVacation(int month);

	
	
}
