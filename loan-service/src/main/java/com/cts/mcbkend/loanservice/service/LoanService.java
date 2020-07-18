package com.cts.mcbkend.loanservice.service;

import java.util.List;

import com.cts.mcbkend.loanservice.model.LoanDto;

public interface LoanService {
	public List<LoanDto> findByUserId(Long userId);
	public LoanDto findByLoanNum(String loanNum);
	public List<LoanDto> fetchAllLoans();
	public LoanDto createLoan(LoanDto loanDto) throws Exception;
	public LoanDto updateLoan(String loanNum, LoanDto loanDto) throws Exception;
}