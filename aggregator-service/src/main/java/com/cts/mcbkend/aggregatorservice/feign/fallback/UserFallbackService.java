package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cts.mcbkend.aggregatorservice.feign.UserService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;

@Component("userFallbackService")
public class UserFallbackService implements UserService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserFallbackService.class);

	@Override
	public ResponseEvent<List<UserDto>> getUserList(String header) throws Exception {
		LOGGER.info("sessionID: "+header +"Inside UserFallbackService");
		AggregatorRestException aggregatorRestException = new AggregatorRestException();
		aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
		aggregatorRestException.setErrorMessage("User service interrupted");
		throw aggregatorRestException;
	}
}
