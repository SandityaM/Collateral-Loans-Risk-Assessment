package com.cognizant.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

/**
 * Test - UserDetailsServiceImpl
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailsServiceImplTest {

	@Mock
	UserRepo repo;

	@InjectMocks
	UserDetailsServiceImpl userDetailsService;

	@Test
	public void loadUserByUsernameTest() {

		MyUser user = new MyUser(1, "admin", "admin");
		when(repo.findByUserName("admin")).thenReturn(user);
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername("admin");
		assertEquals(user.getUserName(), loadUserByUsername.getUsername());
	}

}
