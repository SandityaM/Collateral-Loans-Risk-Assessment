package com.cognizant.collaterals.exceptions;

/**
 * Class for handling CollateralNotFoundExistsException
 *
 */
public class CollateralNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public CollateralNotFoundException(String message) {
		super(message);
	}

}
