package com.cts.mcbkend.documentservice.rest.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.documentservice.rest.common.DocumentCommonExceptionHandlingController;
import com.cts.mcbkend.documentservice.rest.event.ResponseEvent;

@RestController("documentController")
public class DocumentController extends DocumentCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value="/getcatedocumentlist",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<String>> getDocumentList() throws Exception { 
		LOGGER.info("############### Inside of getDocumentList ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(String.valueOf("List of Documents")), HttpStatus.OK);
		
	}
}
