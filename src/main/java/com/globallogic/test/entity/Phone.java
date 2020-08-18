package com.globallogic.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Ricardo Quiroga
 * @version 1.0
 * 
 * Clase que representa la tabla phones
 */
@Entity
@Table(name = "phones")
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String number;
	
	private String citycode;
	
	private String countrycode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getcountrycode() {
		return countrycode;
	}

	public void setcountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", number=" + number + ", citycode=" + citycode + ", countrycode=" + countrycode
				+ "]";
	}
	
	
	

}
