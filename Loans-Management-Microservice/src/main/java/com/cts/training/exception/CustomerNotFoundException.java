package com.cts.training.exception;

public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * This is CustomerLoanNotFoundException constructor
	 * @param message
	 */
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
