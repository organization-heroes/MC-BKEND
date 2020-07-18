package com.cts.mcbkend.loanservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cts.mcbkend.loanservice.entity.LoanEntity;

public interface LoanRepository extends JpaRepository<LoanEntity, Integer> {
	public LoanEntity findById(Long id);
	public List<LoanEntity> findByUserId(Long userId);
	public LoanEntity findByLoanNum(String loanNum);
}