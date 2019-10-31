package com.br.hrxpto.vacation.repository;

import java.time.LocalDate;
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
	
	/**
	 * 
	 * Lista todos empregados que conincide com data de ferias e time.
	 * 
	 * @param idTeam
	 * @param idEmployee 
	 * @param start
	 * @param end
	 * @return List<Employee>
	 */
	@Query(nativeQuery = true,
			value = "SELECT emp.* " + 
			"FROM   vacation vac " + 
			"       INNER JOIN employee emp ON emp.id = vac.employee_id " + 
			"       INNER JOIN team team ON team.id = emp.team_id " + 
			"WHERE  team.id = :idTeam AND emp.id <> :idEmployee" + 
			"       AND ( vac.start_date >= Sysdate() OR vac.end_date >= Sysdate() ) " + 
			"       AND ( :start <= vac.start_date AND :end >= vac.end_date ) " + 
			"       AND vac.start_date <= vac.end_date " + 
			"       OR ( :start BETWEEN vac.start_date AND vac.end_date " + 
			"              OR :end BETWEEN vac.start_date AND vac.end_date ) ")
	public List<Employee> employeesMatchingVacationDate(@Param("idTeam")Long idTeam,
			@Param("idEmployee")Long idEmployee, @Param("start")LocalDate start, @Param("end")LocalDate end);

}
