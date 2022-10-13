package com.cognizant.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is for customizing the exception handler
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

	private String message;
	private LocalDateTime dateTime;
}
