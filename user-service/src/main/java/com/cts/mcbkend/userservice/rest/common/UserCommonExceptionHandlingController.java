package com.cts.mcbkend.userservice.rest.common;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.userservice.rest.exception.UserRestException;
import com.cts.mcbkend.userservice.rest.event.ResponseEvent;
import com.cts.mcbkend.userservice.rest.exception.ErrorResponse;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the custom exception in the rest call
 */
@RestController("userCommonExceptionHandlingController")
public class UserCommonExceptionHandlingController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserCommonExceptionHandlingController.class);
	
	@ExceptionHandler(UserRestException.class)
	public ResponseEntity<ResponseEvent<ErrorResponse>> exceptionHandler(Exception ex) {
		// ex.printStackTrace();
		LOGGER.info(" Inside UserBaseController" + ex.getClass());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		//ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		LOGGER.error("Exception - " + stackTrace);
		UserRestException userException = (UserRestException) ex;
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(userException.getErrorCode().value());
		error.setErrorMessage(userException.getErrorMessage());
		LOGGER.info(userException.getErrorMessage());
		return new ResponseEntity<ResponseEvent<ErrorResponse>>(ResponseEvent.errors(error), userException.getErrorCode());
	}

	
	

}
