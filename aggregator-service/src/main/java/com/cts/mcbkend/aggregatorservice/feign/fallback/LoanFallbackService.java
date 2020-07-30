package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.LoanService;
import com.cts.mcbkend.aggregatorservice.model.LoanDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.ErrorResponse;

@Component("loanFallbackService")
public class LoanFallbackService implements LoanService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoanFallbackService.class);

	@Override
	public ResponseEvent<List<LoanDto>> getMultipleLoans(String authorizationHeader, @RequestParam List<Long> userIds) throws Exception {
		LOGGER.info("sessionID: {} --Inside LoanFallbackService to get multiple loans",authorizationHeader);
		ResponseEvent<List<LoanDto>> responseEvent = new ResponseEvent<List<LoanDto>>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Loan sevice get multiple loan, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}

	@Override
	public ResponseEvent<LoanDto> createNewLoan(String authorizationHeader, LoanDto loanDto) throws Exception {
		LOGGER.info("sessionID: {} --Inside LoanFallbackService to create new loan",authorizationHeader);
		ResponseEvent<LoanDto> responseEvent = new ResponseEvent<LoanDto>();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Loan sevice create new loan, fall back method");
		responseEvent.setError(error);
		return responseEvent;
	}
}
