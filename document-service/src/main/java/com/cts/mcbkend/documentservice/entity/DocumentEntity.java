package com.cts.mcbkend.documentservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="document")
public class DocumentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="doc_seq")
	@SequenceGenerator(name = "doc_seq", sequenceName = "doc_seq", initialValue = 5, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="doc_id", nullable = true, length = 50)
	private String docId;
	
	@Column(name="loan_num", nullable = false, length = 50)
	private String loanNum;
	
	@Column(name="doc_title", nullable = true, length = 100)
	private String docTitle;
	
	@Column(name="doc_desc", nullable = true, length = 100)
	private String docDesc;
	
	@Column(name="doc_location", nullable = true, length = 100)
	private String docLocation;
	
	@Column(name="apprvl_status", nullable = false, length = 15)
	private String apprvlStatus;
	
	@Column(name="user_id", nullable = false)
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public String getDocLocation() {
		return docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public String getApprvlStatus() {
		return apprvlStatus;
	}

	public void setApprvlStatus(String apprvlStatus) {
		this.apprvlStatus = apprvlStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", docId=" + docId + ", loanNum=" + loanNum + ", docTitle=" + docTitle
				+ ", docDesc=" + docDesc + ", docLocation=" + docLocation + ", apprvlStatus=" + apprvlStatus
				+ ", userId=" + userId + "]";
	}
	
	
	
	
}