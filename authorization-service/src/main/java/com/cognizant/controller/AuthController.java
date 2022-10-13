package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.UserNameNumericException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.model.UserCredentials;
import com.cognizant.service.UserDetailsServiceImpl;
import com.cognizant.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Class for Authorization Controller
 */
@RestController
@Slf4j
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

	/**
	 * Method to validate the token
	 * 
	 * @param token1 This is the token send for authentication
	 * @return This returns true/false based on token validity
	 */

	@GetMapping("/validate")
	public ResponseEntity<Boolean> validate(@RequestHeader(name = "Authorization") String token1) {
		String token = token1.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
			
			if (jwtUtil.validateToken(token, user)) {
				System.out.println("=================Inside Validate==================");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Method to check whether login credentials are correct or not
	 * 
	 * @param userCredentials user credentials contain user name and password
	 * @return This returns token on successful login else throws exception
	 */
	@PostMapping("/login")
	public String login(@RequestBody UserCredentials userCredentials) {

		if (userCredentials.getUserName() == null || userCredentials.getPassword() == null
				|| userCredentials.getUserName().trim().isEmpty() || userCredentials.getPassword().trim().isEmpty()) {
			log.debug("Login unsuccessful --> User name or password is empty");
			throw new UserNotFoundException("User name or password cannot be Null or Empty");
		}

		else if (jwtUtil.isNumeric(userCredentials.getUserName())) {
			log.debug("Login unsuccessful --> User name is numeric");
			throw new UserNameNumericException("User name is numeric");
		}

		else {
			try {
				UserDetails user = userDetailsService.loadUserByUsername(userCredentials.getUserName());
				if (user.getPassword().equals(userCredentials.getPassword())) {
					String token = jwtUtil.generateToken(user.getUsername());
					log.debug("Login successful");
					return token;
				} else {
					log.debug("Login unsuccessful --> Invalid password");
					throw new UserNotFoundException("Password is wrong");
				}
			} catch (Exception e) {
				log.debug("Login unsuccessful --> Invalid Credential");
				throw new UserNotFoundException("Invalid Credential");
			}
		}
	}
}
