package com.br.hrxpto.vacation.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.br.hrxpto.vacation.dto.VacationDTO;
import com.br.hrxpto.vacation.model.Employee;
import com.br.hrxpto.vacation.model.Vacation;

/**
 * @author eudes.justino
 *
 */
@Component
public class VacationConverter implements Converter<VacationDTO, Vacation> {

	@Override
	public Vacation convert(VacationDTO dto) {
		Vacation vacation = new Vacation();
		Employee employee = new Employee();
		employee.setId(dto.getIdEmployee());
		vacation.setEmployee(employee);
		vacation.setStartDate(dto.getStartDate());
		vacation.setEndDate(dto.getEndDate());
		return vacation;
	}

	public List<VacationDTO> convert(List<Vacation> vacation) {

		List<VacationDTO> list = new ArrayList<VacationDTO>();
		for (Vacation vac : vacation) {
			list.add(convert(vac));
		}
		return list;
	}

	public Page<VacationDTO> convert(Page<Vacation> vacationPage) {
		return new PageImpl<VacationDTO>(convert(vacationPage.getContent()), vacationPage.getPageable(),
				vacationPage.getTotalElements());
	}

	public VacationDTO convert(Vacation vacation) {
		VacationDTO dto = new VacationDTO();
		dto.setId(vacation.getId());
		dto.setIdEmployee(vacation.getEmployee().getId());
		dto.setStartDate(vacation.getStartDate());
		dto.setEndDate(vacation.getEndDate());
		return dto;
	}

}
