package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.DocumentService;
import com.cts.mcbkend.aggregatorservice.model.DocumentDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;

@Component("docuemntFallbackService")
public class DocumentFallbackService implements DocumentService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentFallbackService.class);

	@Override
	public ResponseEvent<List<DocumentDto>> getMultipleDocuments(String header, @RequestParam List<Long> userIds) throws Exception {
		LOGGER.info("sessionID: "+header +"--Inside DocumentFallbackService");
		AggregatorRestException aggregatorRestException = new AggregatorRestException();
		aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
		aggregatorRestException.setErrorMessage("Document sevice interrupted");
		throw aggregatorRestException;
	}
}
