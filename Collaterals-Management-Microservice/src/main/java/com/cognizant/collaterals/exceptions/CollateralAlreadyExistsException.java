package com.cognizant.collaterals.exceptions;

/**
 * Class for handling CollateralAlreadyExistsException
 *
 */
public class CollateralAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public CollateralAlreadyExistsException(String message) {
		super(message);
	}
}
