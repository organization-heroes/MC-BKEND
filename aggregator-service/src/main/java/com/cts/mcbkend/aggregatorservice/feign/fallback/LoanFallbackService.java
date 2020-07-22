package com.cts.mcbkend.aggregatorservice.feign.fallback;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.LoanService;
import com.cts.mcbkend.aggregatorservice.model.LoanDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;
import com.cts.mcbkend.aggregatorservice.rest.exception.AggregatorRestException;

@Component("loanFallbackService")
public class LoanFallbackService implements LoanService{

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoanFallbackService.class);

	@Override
	public ResponseEvent<List<LoanDto>> getMultipleLoans(String header, @RequestParam List<Long> userIds) throws Exception {
		LOGGER.info("sessionID: "+header +"--Inside LoanFallbackService");
		AggregatorRestException aggregatorRestException = new AggregatorRestException();
		aggregatorRestException.setErrorCode(HttpStatus.BAD_REQUEST);
		aggregatorRestException.setErrorMessage("Loan sevice interrupted");
		throw aggregatorRestException;
	}
}
