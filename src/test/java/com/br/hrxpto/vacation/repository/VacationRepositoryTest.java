package com.br.hrxpto.vacation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.model.Vacation;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@Sql(value = "/load-database-vacation.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database-vacation.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VacationRepositoryTest {
	
	@Autowired
	VacationRepository repository;	
	
	@Test
	public void mustCreateAnVacation() {		
		Vacation vacation = new Vacation();
		Employee employee = new Employee();
		employee.setId(Long.valueOf(1));
		vacation.setEmployee(employee );
		vacation.setStartDate(LocalDate.now());
		vacation.setEndDate(LocalDate.now());
		Vacation save = repository.save(vacation);
		Optional<Vacation> data = repository.findById(save.getId());
		assertThat(data.get().getId()).isEqualTo(save.getId());				
	}
	
	@Test
	public void mustDeleteAnVacation() {
		Optional<Vacation> data = repository.findById(Long.valueOf(1));
		assertThat(data.isPresent()).isTrue();
		repository.delete(data.get());
		data = repository.findById(Long.valueOf(1));
		assertThat(data.isPresent()).isFalse();
	}
	
	@Test
	public void mustUpdateAnVacation() {
		Optional<Vacation> data = repository.findById(Long.valueOf(1));
		assertThat(data.isPresent()).isTrue();
		Vacation vacation = data.get();
		LocalDate endDate = vacation.getEndDate();
		vacation.setEndDate(LocalDate.now());
		Vacation save = repository.save(vacation);
		data = repository.findById(Long.valueOf(1));
		assertThat(save.getEndDate().isAfter(endDate)).isTrue();
		
	}
	
		
	@Test
	public void mustFindByIdAnVacation() {
		Optional<Vacation> data = repository.findById(Long.valueOf(1));
		assertThat(data.get()).isNotNull();
		assertThat(data.get().getId()).isEqualTo(1);
	}

}
