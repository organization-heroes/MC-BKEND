package com.cts.mcbkend.aggregatorservice.service;

import java.util.List;

import com.cts.mcbkend.aggregatorservice.model.UserLoanDto;

public interface AggregatorService {
	public List<UserLoanDto> getAllUserLoanList(String header) throws Exception;
}