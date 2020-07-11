package com.cts.mcbkend.userservice.rest.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.userservice.rest.common.UserCommonExceptionHandlingController;
import com.cts.mcbkend.userservice.rest.event.ResponseEvent;

@RestController("userController")
public class UserController extends UserCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value="/getcateuserlist",method= RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<String>> getUsertList() throws Exception { 
		LOGGER.info("############### Inside of getUsertList ##############");
		return new ResponseEntity<ResponseEvent<String>>(ResponseEvent.response(String.valueOf("List of Users")), HttpStatus.OK);
		
	}
}