package com.globallogic.test.dto;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Validated
public class PhoneDTO implements Serializable {

	@JsonProperty("user number")
	private String number;
	@JsonProperty("user city code")
	private String citycode;
	@JsonProperty("user country code")
	private String countrycode;

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

	public String getCountrycode() {
		return countrycode;
	}

	public void setContrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
	public String toString() {
		return "PhoneDTO [number=" + number + ", citycode=" + citycode + ", contrycode=" + countrycode + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4363949826813121152L;

}
