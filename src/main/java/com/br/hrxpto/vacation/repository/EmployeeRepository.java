package com.br.hrxpto.vacation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.br.hrxpto.vacation.model.Employee;


public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {

	@Query(nativeQuery = true,
			value = "SELECT emp.*" + 
			"FROM   employee emp," + 
			"       ( SELECT id, employee_id, Max(vac.end_date) end_date  FROM   vacation vac" + 
			"         GROUP  BY employee_id) last_vacation" + 
			" WHERE  emp.id = last_vacation.employee_id" + 
			"       AND last_vacation.end_date < Sysdate()" + 
			"       AND Timestampdiff(month, last_vacation.end_date, Sysdate()) <= 24 - :month ")
	public List<Employee> employeesLastVacation( @Param("month") int month);

}
