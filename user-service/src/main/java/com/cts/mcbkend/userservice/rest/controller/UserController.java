package com.cts.mcbkend.userservice.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.userservice.rest.common.UserCommonExceptionHandlingController;
import com.cts.mcbkend.userservice.rest.event.ResponseEvent;

@RestController("userController")
@RequestMapping("/user")
public class UserController extends UserCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/getuserlist", "/v1.1/getuserlist"},method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<String>> getDocumentList() throws Exception { 
		LOGGER.info("############### Inside of getDocumentList ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(String.valueOf("List of Documents")), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/getuserlist"},method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<String>>> getDocuments() throws Exception { 
		LOGGER.info("############### Inside of getDocumentList ##############");
		List<String> docList = new ArrayList<String>();
		docList.add(String.valueOf("List of Documents"));
		return new ResponseEntity<ResponseEvent<List<String>>>(ResponseEvent.response(docList), HttpStatus.OK);
	}
}