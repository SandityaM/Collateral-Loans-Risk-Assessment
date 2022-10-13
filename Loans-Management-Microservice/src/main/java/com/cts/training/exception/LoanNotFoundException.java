package com.cts.training.exception;

/**
 * For Exception for LoanNotFoundException
 */
public class LoanNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * LoanNotFoundException contructor
	 * @param message
	 */
	public LoanNotFoundException(String message) {
		super(message);
	}
}