package com.br.hrxpto.vacation.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.exception.VacationDataPeriodException;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.model.Vacation;
import com.br.hrxpto.vacation.repository.EmployeeRepository;
import com.br.hrxpto.vacation.repository.TeamRepository;
import com.br.hrxpto.vacation.repository.VacationRepository;
import com.br.hrxpto.vacation.service.VacationService;

@Service
public class VacationServiceImpl implements VacationService {

	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Vacation applyVacation(Vacation vacation) throws HiringException, VacationDataPeriodException {
		return saveUpdate(vacation);
	}

	@Override
	public void delete(Long id) {
		vacationRepository.deleteById(id);
	}	
	
	@Override
	public List<Vacation> findByRegistration(String registration) {
		return vacationRepository.findByEmployeeRegistration(registration);
	}
	
	@Override
	public List<Vacation> findAllVacationByRegistration(String registration) {
		return vacationRepository.findByEmployeeRegistration(registration);
	}
	
	@Override
	public List<Vacation> findVacationActiveByRegistration(String registration) {
		return vacationRepository.findVacationActiveByRegistration(registration);
	}
	
	@Override
	public Page<Vacation> findAllPageble(PageRequest pageable) {
		return vacationRepository.findAll(pageable);
	}

	
	private Vacation saveUpdate(Vacation vacation) throws HiringException, VacationDataPeriodException {
		validateHiringTime(vacation.getEmployee());
		validateVacationMatching(vacation);
		return vacationRepository.save(vacation);
	}
	

	/**
	 * Caso a equipe tenha até 4 pessoas, não é permitido duas pessoas tirarem
	 * férias em períodos que tenha ao menos um dia coincidente.
	 * 
	 * @param vacation
	 * @throws VacationDataPeriodException
	 */
	private void validateVacationMatching(Vacation vacation) throws VacationDataPeriodException {
		Optional<Employee> findEmployee = employeeRepository.findById(vacation.getEmployee().getId());
		Long idTeam = findEmployee.get().getTeam().getId();
		Long idEmployee = findEmployee.get().getId();
		int amountOfEmployeePerTeam = teamRepository.amountOfStaffPerTeam(idTeam);
		if (amountOfEmployeePerTeam > 2) {
			List<Employee> employees = employeeRepository.employeesMatchingVacationDate(idTeam,idEmployee, vacation.getStartDate(), vacation.getEndDate());
			if (employees.size() > 0 && !employees.get(0).getId().equals(idEmployee)) {
				throw new VacationDataPeriodException("Two people are not allowed to vacation in "
						+ "periods which have at least one coincident day.");
			}
		}

	}

	/**
	 * O funcionário não pode solicitar férias antes de 1 ano de contratação
	 * 
	 * @param employee
	 * @throws HiringException
	 */
	private void validateHiringTime(Employee employee) throws HiringException {
		Optional<Employee> findEmployee = employeeRepository.findById(employee.getId());
		 LocalDate hiringDate = findEmployee.get().getHiringDate();
		if (ChronoUnit.YEARS.between(hiringDate, LocalDate.now()) < 1) {
			throw new HiringException("Employee cannot apply for vacations before 1 year of hiring");
		}

	}

	

	
	

}
