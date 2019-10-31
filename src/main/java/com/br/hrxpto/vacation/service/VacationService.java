package com.br.hrxpto.vacation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.exception.VacationDataPeriodException;
import com.br.hrxpto.vacation.model.Vacation;

public interface VacationService {
	
	public Vacation applyVacation(Vacation vacation) throws HiringException, VacationDataPeriodException;
	
	public void delete(Long id);
	
	public List<Vacation> findByRegistration(String registration);

	public List<Vacation> findAllVacationByRegistration(String registration);

	public List<Vacation> findVacationActiveByRegistration(String registration);
	
	public Page<Vacation> findAllPageble(PageRequest pageble);

}
