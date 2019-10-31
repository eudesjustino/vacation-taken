package com.br.hrxpto.vacation.controller.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.hrxpto.vacation.converter.EmployeeConverter;
import com.br.hrxpto.vacation.dto.EmployeeDTO;
import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.service.EmployeeService;

/**
 * @author eudes.justino
 *
 */
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeConverter converter;

	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) throws HiringException {		
		Employee employee = converter.convert(employeeDTO);		
		Employee employeeCreate = employeeService.save(employee);
		EmployeeDTO employeDTOConverter = converter.convert(employeeCreate);
		return new ResponseEntity<EmployeeDTO>(employeDTOConverter, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) throws HiringException {
		Employee employee = converter.convert(employeeDTO);	
		employee.setId(id);
		Optional<Employee> employeeFind = employeeService.findById(id);
		employee.setRegistration(employeeFind.get().getRegistration());
		Employee employeeCreate = employeeService.update(employee);
		EmployeeDTO employeDTOConverter = converter.convert(employeeCreate);
		return new ResponseEntity<EmployeeDTO>(employeDTOConverter, new HttpHeaders(), HttpStatus.OK);
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String>  deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);	
		return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		return employeeService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {		
		List<Employee> data = employeeService.findAll();
		return new ResponseEntity<List<Employee>>(data, new HttpHeaders(), HttpStatus.OK);
	}
	

	@GetMapping("/all/pagination")
	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "name") String sortBy) {
		PageRequest pageble = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Employee> date = employeeService.findAllPageble(pageble);
		return new ResponseEntity<Page<Employee>>(date, new HttpHeaders(), HttpStatus.OK);
	}
	
    /**
     * 	Lista todos os funcionários que irão completar 2 anos sem
     *  solicitar férias em no máximo X meses)
     */	
	@GetMapping("/request-vacation/month/{month}")
	public ResponseEntity<List<Employee>> employeesRequestVacation(@PathVariable int month) {
		List<Employee> data = employeeService.employeesRequestVacation(month);
		return new ResponseEntity<List<Employee>>(data, new HttpHeaders(), HttpStatus.OK);  
	}

}
