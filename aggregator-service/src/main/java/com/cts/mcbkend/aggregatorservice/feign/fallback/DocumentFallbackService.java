package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.DocumentService;
import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.ErrorResponse;

@Component("docuemntFallbackService")
public class DocumentFallbackService implements DocumentService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentFallbackService.class);

	@Override
	public ResponseEvent<List<DocumentDto>> getMultipleDocuments(String authorizationHeader, @RequestParam List<Long> userIds) throws Exception {
		LOGGER.info("sessionID: "+authorizationHeader +"--Inside DocumentFallbackService");
		ResponseEvent<List<DocumentDto>> responseEvent = new ResponseEvent<List<DocumentDto>>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Document sevice interrupted, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}
}
