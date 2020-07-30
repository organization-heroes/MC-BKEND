package com.cts.mcbkend.aggregatorservice.feign;

import java.util.List;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.mcbkend.aggregatorservice.feign.fallback.LoanFallbackService;
import com.cts.mcbkend.aggregatorservice.model.LoanDto;
import com.cts.mcbkend.aggregatorservice.rest.event.ResponseEvent;

@RefreshScope
@FeignClient(value="loan-service", fallback=LoanFallbackService.class)
public interface LoanService {
	@GetMapping(path = "/loan/v1.0/get-multiple-loans", produces = {"application/json, application/xml"})
	public ResponseEvent<List<LoanDto>> getMultipleLoans(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @RequestParam List<Long> userIds) throws Exception;
	
	@PostMapping(path = "/loan/v1.2/create-loan", produces = {"application/json, application/xml"})
	public ResponseEvent<LoanDto> createNewLoan(@RequestHeader(value = "AUTH_HEADER", required = true) String authorizationHeader, @RequestBody LoanDto loanDto) throws Exception;
	
}
