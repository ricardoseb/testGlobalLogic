package com.globallogic.test.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globallogic.test.entity.User;
import com.globallogic.test.entity.dao.UserRepository;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * Metodo para guardar usuario de la BD
	 * @param user 
	 * @return User
	 */
	@Override
	@Transactional
	public User save(User user) {
		
		return userRepository.save(user);
	}
	
	/**
	 * Metodo para buscar un usuario en la BD a traves del email
	 * @param email 
	 * @return  Optional <User>
	 */
	@Override
	@Transactional(readOnly = true)
	public User findEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
