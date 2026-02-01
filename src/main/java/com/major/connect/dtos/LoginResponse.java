package com.major.connect.dtos;

public class LoginResponse {

	private String token;
	private int status;
	private String refreshToken;
	
	public LoginResponse(String token, int status, String refreshToken) {
		super();
		this.token = token;
		this.status = status;
		this.refreshToken = refreshToken;
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
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	
}
