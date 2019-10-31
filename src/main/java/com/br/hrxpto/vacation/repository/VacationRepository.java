package com.br.hrxpto.vacation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.br.hrxpto.vacation.model.Vacation;

public interface VacationRepository extends PagingAndSortingRepository<Vacation,Long> {

	List<Vacation> findByEmployeeRegistration(String registration);

	@Query(nativeQuery = true,
			value = "SELECT vac.* " + 
					"FROM   vacation vac " + 
					"       INNER JOIN employee emp ON emp.id = vac.employee_id " + 
					"WHERE  emp.registration = :registration " + 
					"       AND vac.start_date >= Sysdate()")
	List<Vacation> findVacationActiveByRegistration(@Param("registration") String registration);

	

}
