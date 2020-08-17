package com.globallogic.test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Ricardo Quiroga
 * @version 1.0
 * 
 * Clase que representa la tabla usuarios
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@NotEmpty(message= "no debe estar vacio")
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Email(message = "posee formato invalido")
	@Column(nullable = false)
	private String email;
	@NotEmpty
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9].*[0-9]).{4,}$", 
	message = "debe tener una mayuscula, letras minúsculas, y dos numeros")
	@Column(nullable = false)
	private String password;
	
	@NotEmpty
	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List <Phone> phones;
	
	
	public User() {
		phones = new ArrayList<Phone>();
	}

	private String created;
	private String modified;
	private String last_login;
	
	private String token;
	
	private Boolean isactive;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	
	

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
