package com.example.BuyNothingAPI.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {
	
//	@Autowired
//	private TestEntityManager entityManager;
	
	@Test
	public void getName_returnsName() {
		User newUser = new User();
		newUser.setName("Amanda");
		
		assertEquals(null, "Amanda", newUser.getName());
		
		
	}
	
}