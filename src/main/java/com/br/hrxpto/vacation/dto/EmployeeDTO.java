package com.br.hrxpto.vacation.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String registration;
	private String name;
	private LocalDate birhthDate;
	private AddressDTO address;
	private LocalDate hiringDate;
    private byte[] photo;	
	private TeamDTO team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistration() {
		return registration;
	}
	
	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirhthDate() {
		return birhthDate;
	}

	public void setBirhthDate(LocalDate birhthDate) {
		this.birhthDate = birhthDate;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public LocalDate getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	
}
