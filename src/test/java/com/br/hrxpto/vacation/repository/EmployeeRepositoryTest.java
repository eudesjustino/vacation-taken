package com.br.hrxpto.vacation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.hrxpto.vacation.model.Address;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.model.Team;

@ActiveProfiles("qa")
@RunWith(SpringRunner.class)
@Sql(value = "/load-database-employee.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database-employee.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void mustCreateAnEmployee() {
		Employee employee = build();
		Employee saveEmployee = repository.save(employee);
		assertThat(saveEmployee).isNotNull();
		assertThat(saveEmployee.getId()).isGreaterThan(0);
		assertThat(saveEmployee.getName()).isEqualTo("Fulano");
		assertThat(saveEmployee.getRegistration()).isEqualTo("12131415");
	}

	@Test
	public void mustDeleteAnEmployee() {
		Employee employee = build();
		Employee saveEmployee = repository.save(employee);
		Long id = saveEmployee.getId();
		repository.delete(saveEmployee);
		Optional<Employee> findByIdEmployee = repository.findById(id);
		assertThat(findByIdEmployee).isEmpty();
	}

	@Test
	public void mustFindByIdAnEmployee() {
	   		
		Optional<Employee> employee = repository.findById(Long.valueOf(1));
		assertThat(employee).isNotNull();
		assertThat(employee.get().getId()).isGreaterThan(0);
		assertThat(employee.get().getName()).isEqualTo("Eudes Jose");
	}

	@Test
	public void mustFindAllEmployee() {
		
		Optional<Employee> employee1 = repository.findById(Long.valueOf(1));
		Optional<Employee> employee2 = repository.findById(Long.valueOf(2));
		Optional<Employee> employee3 = repository.findById(Long.valueOf(3));

		Iterable<Employee> data = repository.findAll();
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> employee1.get().equals(obj))
				  )
				.isTrue();
		
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> employee2.get().equals(obj))
				  )
				.isTrue();
		
		assertThat(
				   StreamSupport
				      .stream(data.spliterator(), false)
				      .anyMatch(obj -> employee3.get().equals(obj))
				  )
				.isTrue();

	}

	@Test
	public void mustFindAllPagebleAnEmployee() {
						
		Pageable pagebleSortedByName =  PageRequest.of(0, 2, Sort.by("name"));
		Page<Employee> page = repository.findAll(pagebleSortedByName);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getSort()).isEqualTo(Sort.by("name"));
				
		pagebleSortedByName = PageRequest.of(1, 2, Sort.by("name"));
		page = repository.findAll(pagebleSortedByName);
		assertThat(page.getTotalPages()).isEqualTo(2);
		assertThat(page.getSort()).isEqualTo(Sort.by("name"));
		
	}

	public Employee build() {
		Employee employee = new Employee();
		Address address = new Address();
		employee.setAddress(address);
		Team team = new Team();
		team.setId(Long.valueOf(1));
		employee.setTeam(team);
		employee.setBirhthDate(new Date());
		employee.setHiringDate(new Date());
		employee.setName("Fulano");
		employee.setPhoto("data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/7QCcUGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAAIAcAmcAFG5lcDNwYU1QM290a1JNbmlNM3NTHAIoAGJGQk1EMDEwMDBhYmUwMzAwMDBlNzE1MDAwMDM3MjUwMDAwNTcyNjAwMDBkYTI3MDAwMDYzMmEwMDAwNDQzZTAwMDAyYjQzMDAwMDg3NDYwMDAwMmE0YTAwMDAwNDc1MDAwMP/iAhxJQ0NfUFJPRklMRQABAQAAAgxsY21zAhAAAG1udHJSR0IgWFlaIAfcAAEAGQADACkAOWFjc3BBUFBMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD21gABAAAAANMtbGNtcwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACmRlc2MAAAD8AAAAXmNwcnQAAAFcAAAAC3d0cHQAAAFoAAAAFGJrcHQAAAF8AAAAFHJYWVoAAAGQAAAAFGdYWVoAAAGkAAAAFGJYWVoAAAG4AAAAFHJUUkMAAAHMAAAAQGdUUkMAAAHMAAAAQGJUUkMAAAHMAAAAQGRlc2MAAAAAAAAAA2MyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHRleHQAAAAARkIAAFhZWiAAAAAAAAD21gABAAAAANMtWFlaIAAAAAAAAAMWAAADMwAAAqRYWVogAAAAAAAAb6IAADj1AAADkFhZWiAAAAAAAABimQAAt4UAABjaWFlaIAAAAAAAACSgAAAPhAAAts9jdXJ2AAAAAAAAABoAAADLAckDYwWSCGsL9hA/FVEbNCHxKZAyGDuSRgVRd13ta3B6BYmxmnysab9908PpMP///9sAQwAJBgcIBwYJCAgICgoJCw4XDw4NDQ4cFBURFyIeIyMhHiAgJSo1LSUnMiggIC4/LzI3OTw8PCQtQkZBOkY1Ozw5/9sAQwEKCgoODA4bDw8bOSYgJjk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5OTk5/8IAEQgCfwJ/AwAiAAERAQIRAf/EABsAAAMBAQEBAQAAAAAAAAAAAAABAgMEBQYH/8QAGAEBAQEBAQAAAAAAAAAAAAAAAAECAwT/xAAYAQEBAQEBAAAAAAAAAAAAAAAAAQIDBP/aAAwDAAABEQIRAAABmh8ezG5ShqMcMHAxibITbJbBDUCZSYUDACoGMGOBjV0nAxwNMYOAAAEBlqGAmAACasAAABNAAAMQFAAwQwIYimIhxU1".getBytes());
		employee.setRegistration("12131415");
		return employee;
	}

}
