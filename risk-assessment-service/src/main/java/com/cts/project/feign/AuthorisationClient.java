package com.cts.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Proxy interface for authorization service
 */
@FeignClient(name = "authorization-service", url = "${AUTH_SERVICE:http://localhost:8081}")
public interface AuthorisationClient {

	/**
	 * Method for validating the token
	 * 
	 * @param token
	 * @return This returns true if token is valid else returns false
	 */
	@GetMapping("/auth/validate")
	public boolean validate(@RequestHeader(name = "Authorization") String token);
}