package com.hackathon.dto;

import java.io.Serializable;

public class AuthDto implements Serializable {
	private static final long serialVersionUID = -1601338381104047463L;
	private String userName;
	private String token;

	public AuthDto() {
		super();
	}

	public AuthDto(String userName, String token) {
		this.userName = userName;
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
