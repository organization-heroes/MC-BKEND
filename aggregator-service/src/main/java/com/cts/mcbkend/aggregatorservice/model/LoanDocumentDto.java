package com.cts.mcbkend.aggregatorservice.model;

import java.io.Serializable;
import java.util.List;

public class LoanDocumentDto implements Serializable{

	private static final long serialVersionUID = 7251297121329331205L;

	private Long loanId;
	
	private String loanNum;
	
	private String loanType;
	
	private String loanDesc;
	
	private String loanStatus;
	
	private Long version;
	
	private Long lockId;
	
	private List<DocumentDto> documentList;
	
	public LoanDocumentDto () {};

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanDesc() {
		return loanDesc;
	}

	public void setLoanDesc(String loanDesc) {
		this.loanDesc = loanDesc;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getLockId() {
		return lockId;
	}

	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}

	public List<DocumentDto> getDocumentList() {
		return documentList;
	}

	public void setDocumentList(List<DocumentDto> documentList) {
		this.documentList = documentList;
	}

	@Override
	public String toString() {
		return "LoanDocumentDto [loanId=" + loanId + ", loanNum=" + loanNum + ", loanType=" + loanType + ", loanDesc="
				+ loanDesc + ", loanStatus=" + loanStatus + ", version=" + version + ", lockId=" + lockId
				+ ", documentList=" + documentList + "]";
	}
	
	
}
