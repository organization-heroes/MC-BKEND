package com.cts.mcbkend.aggregatorservice.model;

import java.io.Serializable;

public class LoanDto implements Serializable{
	
	private static final long serialVersionUID = -4275540436647047477L;

	private Long id;
	
	private String loanNum;
	
	private String loanType;
	
	private String loanDesc;
	
	private String loanStatus;
	
	private Long version;
	
	private Long lockId;
	
	private Long userId;
	
	public int hashCode(){
       return id.intValue();
    }
     
    public boolean equals(Object obj){
        if (obj instanceof LoanDto) {
        	LoanDto loanDto = (LoanDto) obj;
            return (loanDto.getId().equals(this.getId()) && loanDto.getLoanNum().equals(this.getLoanNum()));
        } else {
            return false;
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LoanDto [id=" + id + ", loanNum=" + loanNum + ", loanType=" + loanType + ", loanDesc=" + loanDesc
				+ ", loanStatus=" + loanStatus + ", version=" + version + ", lockId=" + lockId + ", userId=" + userId
				+ "]";
	}	
	
}