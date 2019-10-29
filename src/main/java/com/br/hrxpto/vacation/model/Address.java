package com.br.hrxpto.vacation.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author eudes.justino
 *
 */
@Embeddable
public class Address implements Serializable{
		
	private static final long serialVersionUID = 1L;	
	private String street;
	private int number;
	private String adjunct;
	private String neighborhood;
	private String city;
	@Enumerated(EnumType.STRING)
	private State state;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAdjunct() {
		return adjunct;
	}
	public void setAdjunct(String adjunct) {
		this.adjunct = adjunct;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}

}
