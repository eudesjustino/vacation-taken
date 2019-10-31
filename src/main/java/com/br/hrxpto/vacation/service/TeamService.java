package com.br.hrxpto.vacation.service;

import java.util.List;

import com.br.hrxpto.vacation.model.Team;

public interface TeamService {
	
	public int amountOfStaffPerTeam(Team team);

	public Team create(Team team);

	public Team update(Team team);

	public void delete(Long id);

	public List<Team> findAll();

}
