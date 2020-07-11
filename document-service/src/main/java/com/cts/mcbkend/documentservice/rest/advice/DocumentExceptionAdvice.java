package com.cts.mcbkend.documentservice.rest.advice;



import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cts.mcbkend.documentservice.rest.event.ResponseEvent;
import com.cts.mcbkend.documentservice.rest.exception.ErrorResponse;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the custom exception if REST API fails to handle
 * any exception
 */
@ControllerAdvice
public class DocumentExceptionAdvice {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentExceptionAdvice.class);
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ResponseEvent<ErrorResponse>> exceptionHandler(Exception ex) {
		LOGGER.info("UserExceptionAdvice:: For Exception");
		ex.printStackTrace();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage("Oops! Something went wrong. Please, contact your administrator.");
		return new ResponseEntity<ResponseEvent<ErrorResponse>>(ResponseEvent.errors(error), HttpStatus.BAD_REQUEST);
	}

}
