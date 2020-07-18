package com.cts.mcbkend.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user")

public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 5, allocationSize=1)
	@Column(name="id")
	private Long id;
	
	@Column(name="f_name", nullable = false, length = 50)
	private String fName;
	
	@Column(name="l_name", nullable = false, length = 50)
	private String lName;
	
	@Column(name="user_name", nullable = false, length = 50)
	private String userName;
	
	@Column(name="password", nullable = false, length = 50)
	private String password;
	
	@Column(name="role", nullable = false, length = 50)
	private String role;
	
	@Column(name="address", nullable = false, length = 200)
	private String address;
	
	@Column(name="state", nullable = false, length = 50)
	private String state;
	
	@Column(name="country", nullable = false, length = 50)
	private String country;
	
	@Column(name="email", nullable = false, length = 50)
	private String email;
	
	@Column(name="pan", nullable = false, length = 20)
	private String pan;
	
	@Column(name="contact_no", nullable = false, length = 15)
	private String contacNo;
	
	@Column(name="dob", nullable = false, length = 15)
	private String dob;
	
	@Column(name="ssn", nullable = false, length = 20)
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