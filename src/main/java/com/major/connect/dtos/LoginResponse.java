package com.major.connect.dtos;

public class LoginResponse {

	private String token;
	private int status;
	public LoginResponse(String token, int status) {
		super();
		this.token = token;
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
