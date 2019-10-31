package com.br.hrxpto.vacation.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.br.hrxpto.vacation.converter.TeamConverter;
import com.br.hrxpto.vacation.dto.TeamDTO;
import com.br.hrxpto.vacation.model.Team;
import com.br.hrxpto.vacation.service.TeamService;


/**
 * @author eudes.justino
 *
 */
@RestController
@RequestMapping("/v1/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	@Autowired
	private TeamConverter converter;

	@PostMapping
	public ResponseEntity<TeamDTO> create(@RequestBody TeamDTO teamDto) {
		Team team = converter.convert(teamDto);
		Team teamSaves = teamService.create(team);
		TeamDTO convert = converter.convert(teamSaves);
		return new ResponseEntity<TeamDTO>(convert, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TeamDTO> update(@RequestBody TeamDTO teamDto, @PathVariable Long id) {
		Team team = converter.convert(teamDto);
		team.setId(id);
		Team teamSaves = teamService.update(team);
		TeamDTO convert = converter.convert(teamSaves);
		return new ResponseEntity<TeamDTO>(convert, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>  delete( @PathVariable Long id) {
		teamService.delete(id);
		return new ResponseEntity<>("Team deleted successfully!", HttpStatus.OK);
	}
	
	@GetMapping
	public List<Team> findAll() {
		return teamService.findAll();
	}	
	
}
