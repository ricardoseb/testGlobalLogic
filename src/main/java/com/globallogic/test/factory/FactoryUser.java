package com.globallogic.test.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.globallogic.test.dto.UserDTO;
import com.globallogic.test.entity.User;

@Component
public class FactoryUser {
	private static final Logger logger = LoggerFactory.getLogger(FactoryUser.class);
	

	
	@SuppressWarnings("null")
	public UserDTO userDTOFactory(User user) {
		
		UserDTO userDTONew = null;

		try {
			userDTONew.setId(user.getId().toString());
			userDTONew.setName(user.getName());
			userDTONew.setEmail(user.getEmail());
			userDTONew.setPhones(user.getPhones());
			userDTONew.setCreated(user.getCreated());
			userDTONew.setModified(user.getModified());
			userDTONew.setLast_login(user.getLast_login());
			userDTONew.setToken(user.getToken());
			userDTONew.setIsactive(user.getIsactive());
			
			return userDTONew;

		} catch (Exception e) {
			logger.error("Error: {}",e.getMessage());
			return userDTONew;
		} finally {
			userDTONew = null;

		}

	}

}
