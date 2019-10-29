package com.br.hrxpto.vacation.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.repository.EmployeeRepository;
import com.br.hrxpto.vacation.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee createUpdate(Employee employee) throws HiringException {
       validateHiringTime(employee);
	   return employeeRepository.save(employee);
	}

	private void validateHiringTime(Employee employee) throws HiringException {
		Date hiringDate = employee.getHiringDate();
		Temporal dt = hiringDate.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		if(ChronoUnit.MONTHS.between(dt , LocalDate.now())<1) {
			throw new HiringException("Employee cannot apply for vacations before 1 year of hiring");
		}
		
	}

	@Override
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
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
