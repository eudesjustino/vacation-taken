package com.br.hrxpto.vacation.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.hrxpto.vacation.converter.VacationConverter;
import com.br.hrxpto.vacation.dto.VacationDTO;
import com.br.hrxpto.vacation.exception.HiringException;
import com.br.hrxpto.vacation.exception.VacationDataPeriodException;
import com.br.hrxpto.vacation.model.Vacation;
import com.br.hrxpto.vacation.service.VacationService;

/**
 * @author eudes.justino
 *
 */
@RestController
@RequestMapping("/v1/vacation")
public class VacationController {

	@Autowired
	private VacationService vacationService;
	
	@Autowired
	private VacationConverter vacationConverter;

	@PostMapping("/apply")
	public ResponseEntity<VacationDTO> applyVacation(@RequestBody VacationDTO vacationDTO)
			throws HiringException, VacationDataPeriodException {
		Vacation vacation = vacationConverter.convert(vacationDTO);
		Vacation vacationSaves = vacationService.applyVacation(vacation);
		VacationDTO convert = vacationConverter.convert(vacationSaves);
		return new ResponseEntity<VacationDTO>(convert, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/apply/{id}")
	public ResponseEntity<VacationDTO> updateVacation(@RequestBody VacationDTO vacationDTO, @PathVariable Long id)
			throws HiringException, VacationDataPeriodException {
		Vacation vacation = vacationConverter.convert(vacationDTO);
		vacation.setId(id);
		Vacation vacationSaves = vacationService.applyVacation(vacation);
		VacationDTO convert = vacationConverter.convert(vacationSaves);
		return new ResponseEntity<VacationDTO>(convert, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		vacationService.delete(id);
		return new ResponseEntity<>("Vacation deleted successfully!", HttpStatus.OK);
	}

	@GetMapping("/all/registration/{registration}")
	public ResponseEntity<List<VacationDTO>> findAllVacationByRegistration(@PathVariable String registration) {
		List<Vacation> data = vacationService.findAllVacationByRegistration(registration);
		List<VacationDTO> convert = vacationConverter.convert(data);
		return new ResponseEntity<List<VacationDTO>>(convert, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/active/registration/{registration}")
	public ResponseEntity<List<VacationDTO>> findVacationActiveByRegistration(@PathVariable String registration) {
		List<Vacation> data = vacationService.findVacationActiveByRegistration(registration);
		List<VacationDTO> convert = vacationConverter.convert(data);
		return new ResponseEntity<List<VacationDTO>>(convert, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Page<VacationDTO>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy) {

		PageRequest pageble = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Vacation> date = vacationService.findAllPageble(pageble);
		Page<VacationDTO> convert = vacationConverter.convert(date);
		return new ResponseEntity<Page<VacationDTO>>(convert, new HttpHeaders(), HttpStatus.OK);

	}

}
