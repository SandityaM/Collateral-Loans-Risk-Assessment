package com.cts.project;

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
 * This is the main class for Risk Assessment
 * 
 * @author POD-6
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class RiskAssessmentServiceApplication {

	/**
	 * Main class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RiskAssessmentServiceApplication.class, args);
	}

	/**
	 * Swagger Configuration
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket configureSwagger2() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/riskManagement/**"))
				.apis(RequestHandlerSelectors.basePackage("com.cts.project")).build();
	}

}
