package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cts.mcbkend.aggregatorservice.feign.UserService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;

@Component("userFallbackService")
public class UserFallbackService implements UserService{


	@Override
	public ResponseEvent<List<UserDto>> getUserList() throws Exception {
		AggregatorRestException aggregatorRestException = new AggregatorRestException();
		aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
		aggregatorRestException.setErrorMessage("User Name can not be empty");
		throw aggregatorRestException;
	}
}
