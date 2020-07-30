package com.cts.mcbkend.aggregatorservice.model;

import java.io.Serializable;

public class DocumentDto implements Serializable{

	private static final long serialVersionUID = -7374706205170310000L;

	private Long id;
	
	private String docId;
	
	private String loanNum;
	
	private String docTitle;
	
	private String docDesc;
	
	private String docLocation;
	
	private String apprvlStatus;
	
	private Long userId;
	
	public DocumentDto () {};

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