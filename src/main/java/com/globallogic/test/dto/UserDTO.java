package com.globallogic.test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.globallogic.test.entity.Phone;

import io.swagger.annotations.ApiModelProperty;


@Validated
public class UserDTO implements Serializable{
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("phones")
	private List <Phone> phones;
	
	public UserDTO() {
		phones = new ArrayList<Phone>();
	}
	
	@JsonProperty("created")
	private String created;
	@JsonProperty("modified")
	private String modified;
	@JsonProperty("last_login")
	private String last_login;
	@JsonProperty("token")
	private String token;
	@JsonProperty("isactive")
	private Boolean isactive;
	
	
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


	@ApiModelProperty(value = "user phones")
	public List<Phone> getPhones() {
		return phones;
	}



	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}


	@ApiModelProperty(value = "created date")
	public String getCreated() {
		return created;
	}



	public void setCreated(String created) {
		this.created = created;
	}


	@ApiModelProperty(value = "modified date")
	public String getModified() {
		return modified;
	}



	public void setModified(String modified) {
		this.modified = modified;
	}


	@ApiModelProperty(value = "last login date")
	public String getLast_login() {
		return last_login;
	}



	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}


	@ApiModelProperty(value = "user token")
	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}


	@ApiModelProperty(value = "user is active")
	public Boolean getIsactive() {
		return isactive;
	}



	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	
	
	
	
	@ApiModelProperty(value = "user id")
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones
				+ ", created=" + created + ", modified=" + modified + ", last_login=" + last_login + ", token=" + token
				+ ", isactive=" + isactive + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1852110424666561760L;
	
	

}
