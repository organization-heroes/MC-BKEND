package com.cts.mcbkend.loanservice.rest.exception;

import java.io.Serializable;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the error message and error code in the rest call
 */
public class ErrorResponse implements Serializable{

	private static final long serialVersionUID = 5674800349849723443L;
	private int errorCode;
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
