package com.globallogic.test;



import com.globallogic.test.entity.Phone;
import com.globallogic.test.entity.User;
import com.globallogic.test.entity.dao.UserRepository;


import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;




import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class UserRepositoryTest  {

	@Autowired
	UserRepository userRepository;

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}
	


	@Test
	public void shouldSaveAndFetchUser() throws Exception {
		User user = new User();
		user.setName("Alan Perez");
		user.setEmail("alan@brito.com");
		user.setIsactive(true);
		user.setPassword("Alan12");
		List<Phone> phones = new ArrayList<Phone>();
		Phone newphone = new Phone();
		newphone.setCitycode("2");
		newphone.setcountrycode("56");
		newphone.setNumber("12345678");
		phones.add(newphone);
		user.setPhones(phones);
		userRepository.save(user);

		User maybeUser = userRepository.findByEmail("alan@brito.com");
		assertThat(maybeUser).isEqualTo(user);

	}


	
	
}
