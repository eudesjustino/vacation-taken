package com.br.hrxpto.vacation.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.br.hrxpto.vacation.dto.AddressDTO;
import com.br.hrxpto.vacation.dto.EmployeeDTO;
import com.br.hrxpto.vacation.dto.TeamDTO;
import com.br.hrxpto.vacation.model.Address;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.model.Team;

@Component
public class EmployeeConverter implements Converter<EmployeeDTO,Employee>{ 

	
	@Override
	public Employee convert(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		Address address = new Address();
		address.setAdjunct(employeeDTO.getAddress().getAdjunct());
		address.setCity(employeeDTO.getAddress().getCity());
		address.setNeighborhood(employeeDTO.getAddress().getNeighborhood());
		address.setNumber(employeeDTO.getAddress().getNumber());
		address.setState(employeeDTO.getAddress().getState());
		address.setStreet(employeeDTO.getAddress().getStreet());
		employee.setAddress(address);
		employee.setBirhthDate(employeeDTO.getBirhthDate());
		employee.setHiringDate(employeeDTO.getHiringDate());
		employee.setName(employeeDTO.getName());
		employee.setPhoto(employeeDTO.getPhoto());
		Team team = new Team();
		team.setId(employeeDTO.getTeam().getId());
		employee.setTeam(team);		
		return employee;
	}
	
	public EmployeeDTO convert(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		AddressDTO addressDTO = new AddressDTO();
		
		addressDTO.setAdjunct(employee.getAddress().getAdjunct());
		addressDTO.setCity(employee.getAddress().getCity());
		addressDTO.setNeighborhood(employee.getAddress().getNeighborhood());
		addressDTO.setStreet(employee.getAddress().getStreet());
		addressDTO.setNumber(employee.getAddress().getNumber());
		addressDTO.setState(employee.getAddress().getState());
		
		employeeDTO.setId(employee.getId());
		employeeDTO.setAddress(addressDTO );
		employeeDTO.setBirhthDate(employee.getBirhthDate());
		employeeDTO.setHiringDate(employee.getHiringDate());
		employeeDTO.setName(employee.getName());
		employeeDTO.setPhoto(employee.getPhoto());
		employeeDTO.setRegistration(employee.getRegistration());
		TeamDTO team =new TeamDTO();
		team.setId(employee.getTeam().getId());
		team.setName(employee.getTeam().getName());
		employeeDTO.setTeam(team );
		
		return employeeDTO;
	}

}
