package com.globallogic.test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.annotations.ApiModelProperty;


@Validated
public class UserDTO implements Serializable{
	
	
	@NotEmpty(message= "no debe estar vacio")
	@JsonProperty("name")
	private String name;
	@NotEmpty
	@Email(message = "posee formato invalido")
	@JsonProperty("email")
	private String email;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9].*[0-9]).{4,}$", 
	message = "debe tener una mayuscula, letras minúsculas, y dos numeros")
	@JsonProperty("password")
	private String password;
	@JsonProperty("phones")
	private List <PhoneDTO> phones;
	
	public UserDTO() {
		phones = new ArrayList<PhoneDTO>();
	}
	
	
	
	@ApiModelProperty(example = "Juan Rodriguez", value = "user name")
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	@ApiModelProperty(example = "juan@rodriguez.org​",value = "user email")
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	@ApiModelProperty(example = "Hunter22",value = "user password")
	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}


	@ApiModelProperty(value = "phones")
	public List<PhoneDTO> getPhones() {
		return phones;
	}



	public void setPhones(List<PhoneDTO> phones) {
		this.phones = phones;
	}




	

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones + "]";
	}






	/**
	 * 
	 */
	private static final long serialVersionUID = -1852110424666561760L;
	
	

}
