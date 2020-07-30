package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cts.mcbkend.aggregatorservice.feign.UserService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.ErrorResponse;

@Component("userFallbackService")
public class UserFallbackService implements UserService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserFallbackService.class);

	@Override
	public ResponseEvent<List<UserDto>> getUserList(String authorizationHeader) throws Exception {
		LOGGER.info("sessionID: {} --Inside UserFallbackService to get user list", authorizationHeader);
		ResponseEvent<List<UserDto>> responseEvent = new ResponseEvent<List<UserDto>>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Get user list service interrupted, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}

	@Override
	public ResponseEvent<UserDto> registerUser(String authorizationHeader, UserDto userDto) throws Exception {
		LOGGER.info("sessionID: {} --Inside UserFallbackService to register a user", authorizationHeader);
		ResponseEvent<UserDto> responseEvent = new ResponseEvent<UserDto>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Register user service interrupted, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}

	@Override
	public ResponseEvent<String> deleteUser(String authorizationHeader, Long userId, UserDto userDto) throws Exception {
		LOGGER.info("sessionID: {} --Inside UserFallbackService to delete a user", authorizationHeader);
		ResponseEvent<String> responseEvent = new ResponseEvent<String>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Delete user service interrupted, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}

	@Override
	public ResponseEvent<UserDto> loginUser(UserDto userDto) throws Exception {
		LOGGER.info("Inside UserFallbackService for user login");
		ResponseEvent<UserDto> responseEvent = new ResponseEvent<UserDto>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("login user service interrupted, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}
}
