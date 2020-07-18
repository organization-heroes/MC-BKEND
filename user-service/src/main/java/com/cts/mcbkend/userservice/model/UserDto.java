package com.cts.mcbkend.userservice.model;

import java.io.Serializable;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = -5976898854489260010L;

	private Long id;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName + ", password="
				+ password + ", role=" + role + ", address=" + address + ", state=" + state + ", country=" + country
				+ ", email=" + email + ", pan=" + pan + ", contacNo=" + contacNo + ", dob=" + dob + ", ssn=" + ssn
				+ "]";
	}
	
	
	
}