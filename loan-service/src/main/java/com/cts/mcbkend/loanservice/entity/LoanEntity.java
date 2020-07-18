package com.cts.mcbkend.loanservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="loan")
public class LoanEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="loan_seq")
	@SequenceGenerator(name = "loan_seq", sequenceName = "loan_seq", initialValue = 5, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="loan_num", nullable = true, length = 50)
	private String loanNum;
	
	@Column(name="loan_type", nullable = false, length = 100)
	private String loanType;
	
	@Column(name="loan_desc", nullable = true, length = 100)
	private String loanDesc;
	
	@Column(name="loan_status", nullable = true, length = 20)
	private String loanStatus;
	
	@Column(name="version", nullable = false)
	private Long version;
	
	@Column(name="lock_id", nullable = false, length = 20)
	private Long lockId;
	
	@Column(name="user_id", nullable = false)
	private Long userId;

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

	@Override
	public String toString() {
		return "LoanEntity [id=" + id + ", loanNum=" + loanNum + ", loanType=" + loanType + ", loanDesc=" + loanDesc
				+ ", loanStatus=" + loanStatus + ", version=" + version + ", lockId=" + lockId + ", userId=" + userId
				+ "]";
	}

}