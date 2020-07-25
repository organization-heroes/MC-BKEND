package com.cts.mcbkend.aggregatorservice.model;

import java.io.Serializable;
import java.util.List;

public class UserLoanDto implements Serializable{

	private static final long serialVersionUID = 7216720929962286558L;
	
	private Long userId;
	
	private String fName;
	
	private String lName;
	
	private String userName;
	
	private String password;
	
	private String role;
	
	private String address;
	
	private String state;
	
	private String country;
	
	private String email;
	
	private String pan;
	
	private String contacNo;
	
	private String dob;
	
	private String ssn;
	
	private String authToken;
	
	private List<LoanDocumentDto> loanDocumentList;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContacNo() {
		return contacNo;
	}

	public void setContacNo(String contacNo) {
		this.contacNo = contacNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public List<LoanDocumentDto> getLoanDocumentList() {
		return loanDocumentList;
	}

	public void setLoanDocumentList(List<LoanDocumentDto> loanDocumentList) {
		this.loanDocumentList = loanDocumentList;
	}

	@Override
	public String toString() {
		return "UserLoanDto [userId=" + userId + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", password=" + password + ", role=" + role + ", address=" + address + ", state=" + state
				+ ", country=" + country + ", email=" + email + ", pan=" + pan + ", contacNo=" + contacNo + ", dob="
				+ dob + ", ssn=" + ssn + ", loanDocumentList=" + loanDocumentList + "]";
	}
	
	

}
