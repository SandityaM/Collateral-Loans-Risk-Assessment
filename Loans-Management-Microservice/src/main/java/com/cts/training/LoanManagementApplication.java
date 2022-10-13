package com.cts.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author POD-6
 * This is the Main Application
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class LoanManagementApplication {

	/**
	 * Main Function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LoanManagementApplication.class, args);
	}
	/**
	 * ConfigureSwagger2
	 * @return Docket
	 */
	@Bean
	public Docket configureSwagger2() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/loan-management/**"))
				.apis(RequestHandlerSelectors.basePackage("com.cts.training")).build();
	}

}
