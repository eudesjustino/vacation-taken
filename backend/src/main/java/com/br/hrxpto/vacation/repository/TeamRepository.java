package com.br.hrxpto.vacation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.hrxpto.vacation.model.Team;

public interface TeamRepository extends JpaRepository<Team,Long>{

	@Query(nativeQuery = true,
			value ="SELECT DISTINCT qt_funcionario " + 
			"FROM   (SELECT Count(employee.id) qt_funcionario, " + 
			"               team.id, " + 
			"               team.name " + 
			"        FROM   employee " + 
			"               INNER JOIN team " + 
			"                       ON employee.team_id = team.id " + 
			"        GROUP  BY team.name) funcionario_team, " + 
			"       employee " + 
			"WHERE  employee.team_id = :id " + 
			"       AND employee.team_id = funcionario_team.id ")
	public int amountOfStaffPerTeam(@Param("id") Long id);

	

}
