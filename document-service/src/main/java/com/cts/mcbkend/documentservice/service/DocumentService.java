package com.cts.mcbkend.documentservice.service;

import java.util.List;

import com.cts.mcbkend.documentservice.model.DocumentDto;

public interface DocumentService {
	public List<DocumentDto> findByUserId(Long userId);
	public List<DocumentDto> findByApprvlStatusIsAvalilable();
	public List<DocumentDto> findByLoanNum(String loanNum);
	public List<DocumentDto> findByUserIdAndLoanNum(Long userId, String loanNum);
	public List<DocumentDto> findAlldocuments();
	public String updateApprovalStatus(DocumentDto documentDto);
	public String deleteByLoanNumAndDocId(Long documentIndex, DocumentDto documentDto);
	public DocumentDto findByLoanNumAndDocId(String loanNum, String docId);
	public DocumentDto addLoanDocument(DocumentDto documentDto) throws Exception;
	public DocumentDto updateLoanDocument(Long docPrimaryId, DocumentDto documentDto) throws Exception;
}