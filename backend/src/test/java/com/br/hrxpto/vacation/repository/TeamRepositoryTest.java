package com.br.hrxpto.vacation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.hrxpto.vacation.model.Team;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@Sql(value = "/load-database-team.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database-team.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TeamRepositoryTest {
	
	@Autowired
	private TeamRepository repository;
	
	@Test
	public void mustCreateAnTeam() {		
		Team team = new Team();
		team.setName("DEV");		
		Team save = repository.save(team);
		assertThat(save.getId()).isGreaterThan(0);
		assertThat(save.getName()).isEqualTo(team.getName());		
	}
	
	@Test
	public void mustDeleteAnTeam() {		
		Team team = new Team();
		team.setName("DEV");		
		Team save = repository.save(team);	
		Long id = save.getId();
		assertThat(id).isGreaterThan(0);
		assertThat(save.getName()).isEqualTo(team.getName());
		repository.delete(save);
		Optional<Team> data = repository.findById(id);
		assertThat(data.isPresent()).isFalse();
			
	}
	
	@Test
	public void mustFindByIdAnTeam() {
		
		Optional<Team> data = repository.findById(Long.valueOf(1));
		assertThat(data).isNotNull();
		assertThat(data.get().getName()).isEqualTo("DEV");
		
	}
	
	@Test
	public void mustFindAllTeam() {
		
		Optional<Team> team1 = repository.findById(Long.valueOf(1));
		Optional<Team> team2 = repository.findById(Long.valueOf(2));
		Optional<Team> team3 = repository.findById(Long.valueOf(3));

		Iterable<Team> data = repository.findAll();
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> team1.get().equals(obj))
				  )
				.isTrue();
		
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> team2.get().equals(obj))
				  )
				.isTrue();
		
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> team3.get().equals(obj))
				  )
				.isTrue();
	}

}
