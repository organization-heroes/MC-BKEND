package com.cts.mcbkend.documentservice.rest.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object is custom exception
 */
public class DocumentRestException extends Exception {
	
	private static final long serialVersionUID = 4689640754895923304L;
	private String errorMessage;
	private HttpStatus errorCode;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

}
