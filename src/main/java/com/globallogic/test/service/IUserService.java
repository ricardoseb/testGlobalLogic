package com.globallogic.test.service;




import com.globallogic.test.entity.User;




/**
 * 
 * @author Ricardo Quiroga
 * 
 *
 */
public interface IUserService {

	
	public User save(User user);
	public User findEmail(String email);
}
