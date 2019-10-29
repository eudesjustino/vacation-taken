package com.br.hrxpto.vacation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.hrxpto.vacation.model.Vacation;

public interface VacationRepository extends JpaRepository<Vacation,Long> {

}
