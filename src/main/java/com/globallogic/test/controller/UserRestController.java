package com.globallogic.test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.test.dto.UserDTO;
import com.globallogic.test.entity.Phone;
import com.globallogic.test.entity.User;
import com.globallogic.test.factory.FactoryUser;
import com.globallogic.test.service.IUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;



/**
 * 
 * @author Ricardo Quiroga
 *
 */
@Api(value = "SpringBoot-test", description = "Global Logic Test API")
@RestController
@RequestMapping(value = "user", produces = "application/json; charset=UTF-8", consumes = "application/json")
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private IUserService userService;
	
	@Autowired
	FactoryUser factoryUser;
	
	
	@Value("${secret.key}")
	private String TOKEN_SECRET;

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
		UserDTO usernew = null;
		User userEmail = null;
		Date date = null;
		Map<String, Object> response = new HashMap<>();
		logger.info("[UserRestController:create] Start - validate request");
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("mensaje", errors);
			logger.error("[UserRestController:create] Error: {}",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			logger.info("[UserRestController:create] User validate - search user email: {}",user.getEmail());
			userEmail = userService.findEmail(user.getEmail());
			if (userEmail == null) {
				logger.info("[UserRestController:create] User email not registered - create proccess");
				date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				user.setPassword(user.getPassword());
				user.setCreated(formatter.format(date));
				user.setLast_login(formatter.format(date));
				user.setIsactive(true);
				user.setToken(getJWTToken());
				List<Phone> phones = new ArrayList<Phone>();
				for (Phone telefonos : user.getPhones()) {
					Phone newphone = new Phone();
					newphone.setCitycode(telefonos.getCitycode());
					newphone.setcountrycode(telefonos.getcountrycode());
					newphone.setNumber(telefonos.getNumber());
					phones.add(newphone);
				}
				user.setPhones(phones);
				userService.save(user);
				usernew = factoryUser.userDTOFactory(user);
				logger.info("[UserRestController:create] End - Created user");
			} else {
				response.put("mensaje", "El correo ya esta registrado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			logger.error("[UserRestController:create] Error: " + e.getMessage());
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return new ResponseEntity<UserDTO>(usernew, HttpStatus.CREATED);

	}


	/**
	 * Metodo que genera un token
	 * @return un JWT en formato String
	 */
	public String getJWTToken() {
		final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
		String token = Jwts.builder().claim("authorities", authorities).signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
		return "Bearer " + token;

	}

}
