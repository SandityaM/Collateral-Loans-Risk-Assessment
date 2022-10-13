package com.cts.training.collateralwebportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CollateralWebPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollateralWebPortalApplication.class, args);
	}

}
