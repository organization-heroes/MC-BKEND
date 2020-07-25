package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.cts.mcbkend.aggregatorservice.feign.AuthCenter;

@Component("authFallbackService")
public class AuthFallbackService implements AuthCenter{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AuthFallbackService.class);

	@Override
	public ResponseEntity<Object> getUserAuthorization(Map<String, String> requestBody) throws Exception {
		LOGGER.info("Inside AuthFallbackService to get Bearer Token");
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

}
