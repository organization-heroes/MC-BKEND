package com.cts.mcbkend.userservice.rest.exception;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object is custom exception
 */
public class UserRestException extends Exception {
	
	private static final long serialVersionUID = 4689640754895923304L;
	private String errorMessage;
	private String errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
