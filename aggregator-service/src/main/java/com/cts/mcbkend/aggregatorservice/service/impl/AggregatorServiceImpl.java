package com.cts.mcbkend.aggregatorservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.mcbkend.aggregatorservice.feign.UserService;
import com.cts.mcbkend.aggregatorservice.model.UserDto;
import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;
import com.cts.mcbkend.aggregatorservice.rest.controller.AggregatorController;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.service.AggregatorService;

@Service("AggregatorService")
public class AggregatorServiceImpl implements AggregatorService {

	@Autowired
	private UserService userService;
	
	@Override
	public List<UserLoanDto> getAllUserLoanList() throws Exception {
		List<UserDto> userDtoList = null;
		List<UserLoanDto> userLoanDtoList = new ArrayList<UserLoanDto>();
		UserLoanDto userLoanDto = null;
		ResponseEvent<List<UserDto>> listUserDtoResponse = userService.getUserList();
		userDtoList = listUserDtoResponse.getPayload();
		for(UserDto eachUserDto: userDtoList) {
			userLoanDto = new UserLoanDto();
			userLoanDto.setUserId(eachUserDto.getId());
			userLoanDto.setAddress(eachUserDto.getAddress());
			userLoanDto.setContacNo(eachUserDto.getContacNo());
			userLoanDto.setCountry(userLoanDto.getCountry());
			userLoanDto.setDob(eachUserDto.getDob());
			userLoanDto.setEmail(eachUserDto.getEmail());
			userLoanDto.setfName(eachUserDto.getfName());
			userLoanDto.setlName(eachUserDto.getlName());
			userLoanDto.setPan(eachUserDto.getPan());
			userLoanDto.setRole(eachUserDto.getRole());
			userLoanDto.setSsn(eachUserDto.getSsn());
			userLoanDto.setState(eachUserDto.getState());
			userLoanDto.setUserName(eachUserDto.getUserName());
			userLoanDtoList.add(userLoanDto);
		}
		return userLoanDtoList;
	}

	
	
}