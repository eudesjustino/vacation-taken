package com.br.hrxpto.vacation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.hrxpto.vacation.model.Team;
import com.br.hrxpto.vacation.repository.TeamRepository;
import com.br.hrxpto.vacation.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public int amountOfStaffPerTeam(Team team) {
		Long id = team.getId();
		return teamRepository.amountOfStaffPerTeam(id);
	}

	@Override
	public Team create(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Team update(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void delete(Long id) {
		teamRepository.deleteById(id);
	}

	@Override
	public List<Team> findAll() {
		return teamRepository.findAll();
	}

}
