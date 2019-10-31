package com.br.hrxpto.vacation.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.br.hrxpto.vacation.dto.TeamDTO;
import com.br.hrxpto.vacation.model.Team;

/**
 * @author eudes.justino
 *
 */
@Component
public class TeamConverter implements Converter<TeamDTO,Team>{

	
	@Override
	public Team convert(TeamDTO dto) {
		Team team = new Team();
		team.setId(dto.getId());
		team.setName(dto.getName());
		return team;
	}
	
	public TeamDTO convert(Team team) {
		TeamDTO dto = new TeamDTO();
		dto.setId(team.getId());
		dto.setName(team.getName());
		return null;
	}

}
