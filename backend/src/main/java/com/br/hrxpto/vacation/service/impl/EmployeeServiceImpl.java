package com.br.hrxpto.vacation.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.repository.EmployeeRepository;
import com.br.hrxpto.vacation.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee save(Employee employee) {
		String registration = generateRegistration();
		employee.setRegistration(registration);
		return employeeRepository.save(employee);
	}

	private String generateRegistration() {
		String[] split = UUID.randomUUID().toString().split("-");
		String registratio = split[0] + split[1] + split[2] + split[3] + split[4];
		return registratio.substring(3, 12);
	}

	@Override
	public void delete(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Page<Employee> findAllPageble(PageRequest pageble) {
		return employeeRepository.findAll(pageble);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> employeesRequestVacation(int month) {
		return employeeRepository.employeesLastVacation(month);

	}

}
