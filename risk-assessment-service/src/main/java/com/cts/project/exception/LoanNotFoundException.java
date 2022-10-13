package com.cts.project.exception;

/**
 * Class for handling LoanNotFoundExistsException
 *
 */
public class LoanNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public LoanNotFoundException(String message) {
		super(message);
	}
}
