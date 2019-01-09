package com.example.BuyNothingAPI.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.BuyNothingAPI.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void getUser_from_repository() {
		User amanda = new User();
		amanda.setName("Amanda");
		
		entityManager.persist(amanda);
		entityManager.flush();
		
		Optional <User> userOption = userRepository.findById(amanda.getId());
		assertThat(userOption).isNotEmpty();
		
		if (userOption.isPresent()) {
			User foundUser = userOption.get();
			assertThat(amanda.getId()).isEqualTo(foundUser.getId());
		} 
		
		
		
		
		
	}
	

}
