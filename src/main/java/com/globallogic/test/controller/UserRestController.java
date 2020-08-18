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

import com.globallogic.test.dto.PhoneDTO;
import com.globallogic.test.dto.UserDTO;
import com.globallogic.test.entity.Phone;
import com.globallogic.test.entity.User;

import com.globallogic.test.service.IUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



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


	@Value("${secret.key}")
	private String TOKEN_SECRET;

	@ApiOperation(value = "Create a user", notes = "By passing in the appropriate options, you can create a user ", response = User.class)
	@ApiResponses(value = { 
	        @ApiResponse(code = 201, message = "Created", response = User.class),
	        @ApiResponse(code = 400, message = "bad input parameter"),
	        @ApiResponse(code = 403, message = "Forbidden"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 500, message = "Server Error") })
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
		User usernew = null;
		User userEmail = null;
		User userSaved = null;
		Date date = null;
		Map<String, Object> response = new HashMap<>();
		logger.info("[UserRestController:create] Start - validate request");
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("mensaje", errors);
			logger.error("[UserRestController:create] Error: {}", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			logger.info("[UserRestController:create] User validate - search user email: {}", userDTO.getEmail());
			userEmail = userService.findEmail(userDTO.getEmail());
			if (userEmail == null) {
				logger.info("[UserRestController:create] User email not registered - create proccess");
				date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				usernew = new User();
				usernew.setName(userDTO.getName());
				usernew.setEmail(userDTO.getEmail());
				usernew.setPassword(userDTO.getPassword());
				usernew.setCreated(formatter.format(date));
				usernew.setLast_login(formatter.format(date));
				usernew.setIsactive(true);
				usernew.setToken(getJWTToken());
				List<Phone> phones = new ArrayList<Phone>();
				for (PhoneDTO telefonos : userDTO.getPhones()) {
					Phone newphone = new Phone();
					newphone.setCitycode(telefonos.getCitycode());
					newphone.setcountrycode(telefonos.getCountrycode());
					newphone.setNumber(telefonos.getNumber());
					phones.add(newphone);
				}
				usernew.setPhones(phones);
				logger.info("User to persist: {}", usernew.toString());
				userSaved = userService.save(usernew);
				logger.info("[UserRestController:create] End - Created user");
				return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);

			} else {
				response.put("mensaje", "El correo ya esta registrado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (DataAccessException e) {
			logger.error("[UserRestController:create] Error: " + e.getMessage());
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("mensaje", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
			usernew = null;
			userSaved = null;
		}

	}

	/**
	 * Metodo que genera un token
	 * 
	 * @return un JWT en formato String
	 */
	public String getJWTToken() {
		final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
		String token = Jwts.builder().claim("authorities", authorities).signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
		return "Bearer " + token;

	}

}
