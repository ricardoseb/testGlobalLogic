package com.globallogic.test.dto;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Validated
public class PhoneDTO implements Serializable {

	@JsonProperty("number")
	private String number;
	@JsonProperty("citycode")
	private String citycode;
	@JsonProperty("countrycode")
	private String countrycode;

	@ApiModelProperty(example = "1234567")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@ApiModelProperty(example = "2")
	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	@ApiModelProperty(example = "56")
	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	

	



	/**
	 * 
	 */
	private static final long serialVersionUID = -4363949826813121152L;

	

}
