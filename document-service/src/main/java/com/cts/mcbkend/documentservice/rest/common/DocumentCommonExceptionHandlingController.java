package com.cts.mcbkend.documentservice.rest.common;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.documentservice.rest.exception.DocumentRestException;
import com.cts.mcbkend.documentservice.rest.event.ResponseEvent;
import com.cts.mcbkend.documentservice.rest.exception.ErrorResponse;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the custom exception in the rest call
 */
@RestController("documentCommonExceptionHandlingController")
public class DocumentCommonExceptionHandlingController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentCommonExceptionHandlingController.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(DocumentRestException.class)
	public ResponseEntity<ResponseEvent> exceptionHandler(Exception ex) {
		// ex.printStackTrace();
		LOGGER.info(" Inside DocumentBaseController" + ex.getClass());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		//ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		LOGGER.error("Exception - " + stackTrace);
		DocumentRestException documentException = (DocumentRestException) ex;
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(documentException.getErrorCode().value());
		error.setErrorMessage(documentException.getErrorMessage());
		LOGGER.info(documentException.getErrorMessage());
		return new ResponseEntity<ResponseEvent>(ResponseEvent.errors(error), documentException.getErrorCode());
	}

	
	

}
