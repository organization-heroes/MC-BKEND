package com.cts.mcbkend.aggregatorservice.rest.common;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.ErrorResponse;

/**
 * 
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose This object will return the custom exception in the rest call
 */
@RestController("aggregatorCommonExceptionHandlingController")
public class AggregatorCommonExceptionHandlingController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AggregatorCommonExceptionHandlingController.class);
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AggregatorRestException.class)
	public ResponseEntity<ResponseEvent> exceptionHandler(Exception ex) {
		// ex.printStackTrace();
		LOGGER.info(" Inside AggregatorBaseController {}", ex.getClass());
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		LOGGER.error("Exception - {}", stackTrace);
		AggregatorRestException documentException = (AggregatorRestException) ex;
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(documentException.getErrorCode().value());
		error.setErrorMessage(documentException.getErrorMessage());
		LOGGER.info(documentException.getErrorMessage());
		return new ResponseEntity<ResponseEvent>(ResponseEvent.errors(error), documentException.getErrorCode());
	}

	
	

}
