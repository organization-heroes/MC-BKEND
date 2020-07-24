package com.cts.mcbkend.aggregatorservice.service;

import java.util.List;

import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;

public interface AggregatorService {
	public List<UserLoanDto> getAllUserLoanList(String authorizationHeader) throws Exception;

	public UserLoanDto createUserWithLoan(String authorizationHeader, UserLoanDto userLoanDto) throws Exception;
}