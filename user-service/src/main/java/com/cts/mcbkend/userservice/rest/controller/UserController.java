package com.cts.mcbkend.userservice.rest.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mcbkend.userservice.model.UserDto;
import com.cts.mcbkend.userservice.rest.common.UserCommonExceptionHandlingController;
import com.cts.mcbkend.userservice.rest.event.ResponseEvent;
import com.cts.mcbkend.userservice.rest.exception.UserRestException;
import com.cts.mcbkend.userservice.service.UserService;

@RestController("userController")
@RequestMapping("/user")
public class UserController extends UserCommonExceptionHandlingController{ 
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Value(value = "${custom.instance.id}")
	private String customInstanceId;

	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-user/userName/{userName}", "/v1.1/get-user/userName/{userName}"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<UserDto>> getUserByUserName(@PathVariable("userName") String userName) throws Exception { 
		LOGGER.info(customInstanceId + "=====> Looking for the user by user name");
		if(StringUtils.isEmpty(userName)) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("User Name can not be empty");
			throw userRestException;
		}
		UserDto userDto = null;
		userDto=userService.findByUserName(userName);
		if(userDto==null) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.NOT_FOUND);
			userRestException.setErrorMessage("Invalid user, please enter valid user name!");
			throw userRestException;
		}
		return new ResponseEntity<ResponseEvent<UserDto>>(ResponseEvent.response(userDto), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/get-user-list", "/v1.1/get-user-list"},method= RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<List<UserDto>>> getUserList() throws Exception { 
		LOGGER.info(customInstanceId + "=====> Getting the list of user");
		List<UserDto> userDtoList = null;
		userDtoList=userService.findAllUsers();
		if(userDtoList==null || userDtoList.isEmpty() || userDtoList.size()<1) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.NO_CONTENT);
			userRestException.setErrorMessage("No value found for user");
			throw userRestException;
		}
		return new ResponseEntity<ResponseEvent<List<UserDto>>>(ResponseEvent.response(userDtoList), HttpStatus.OK);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.2/register"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<UserDto>> registerUser(@RequestBody UserDto userDto) throws Exception {
		LOGGER.info(customInstanceId + "=====> Registration of user");
		if(StringUtils.isEmpty(userDto.getfName()) || StringUtils.isEmpty(userDto.getlName()) || StringUtils.isEmpty(userDto.getContacNo()) 
				|| StringUtils.isEmpty(userDto.getAddress()) || StringUtils.isEmpty(userDto.getCountry()) || StringUtils.isEmpty(userDto.getState())
				|| StringUtils.isEmpty(userDto.getDob()) || StringUtils.isEmpty(userDto.getEmail()) || StringUtils.isEmpty(userDto.getPan()) 
				|| StringUtils.isEmpty(userDto.getPassword())|| StringUtils.isEmpty(userDto.getRole()) || StringUtils.isEmpty(userDto.getSsn()) 
				|| StringUtils.isEmpty(userDto.getUserName()) ) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("User registration data can not be empty !!");
			throw userRestException;
		}
		UserDto registerUserDto = null;
		registerUserDto = userService.registerUser(userDto);
		if(registerUserDto.getId()==null || registerUserDto.getId()<1) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.FORBIDDEN);
			userRestException.setErrorMessage("User registration failed, please try again!");
			throw userRestException;
		}
		return new ResponseEntity<ResponseEvent<UserDto>>(ResponseEvent.response(registerUserDto), HttpStatus.CREATED);
		
	}
	
	/**
	 * 
	 * @return ResponseEntity as list of category, all null values in object will be ignored
	 * @throws Exception
	 */
	@RequestMapping(value={"/v1.0/login", "/v1.1/login"},method= RequestMethod.POST, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent<UserDto>> loginUser(@RequestBody UserDto userDto) throws Exception { 
		LOGGER.info(customInstanceId + "=====> User login");
		String userName = userDto.getUserName();
		String password = userDto.getPassword();
		if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.BAD_REQUEST);
			userRestException.setErrorMessage("User Name and Pasword can not be empty");
			throw userRestException;
		}
		UserDto loginUserDto = null;
		loginUserDto=userService.findByUserNameAndPassword(userName, password);
		if(loginUserDto==null) {
			UserRestException userRestException = new UserRestException();
			userRestException.setErrorCode(HttpStatus.NOT_FOUND);
			userRestException.setErrorMessage("Invalid User credential!");
			throw userRestException;
		}
		return new ResponseEntity<ResponseEvent<UserDto>>(ResponseEvent.response(loginUserDto), HttpStatus.OK);
		
	}
}