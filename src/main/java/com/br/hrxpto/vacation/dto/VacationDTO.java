package com.br.hrxpto.vacation.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author eudes.justino
 *
 */
public class VacationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long idEmployee;
	private LocalDate startDate;
	private LocalDate endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

}
