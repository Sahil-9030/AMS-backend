package com.major.connect.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.major.connect.models.RefreshToken;
import com.major.connect.models.UserInfo;
import com.major.connect.repositories.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	private long expiryTime = 1000*60*60*24*7;
	
	@Autowired
	RefreshTokenRepository refreshTokenRepository;
	
	public RefreshToken createRefreshToken(UserInfo user) {
		
		
		refreshTokenRepository.findByUser(user)
        .ifPresent(refreshTokenRepository::delete);
		
		RefreshToken refreshToken = new RefreshToken();
		
		refreshToken.setExpiryDate(new Date(System.currentTimeMillis() +expiryTime));
		refreshToken.setUser(user);
		refreshToken.setRevoked(false);
		refreshToken.setToken(UUID.randomUUID().toString());
		
		System.err.println("Token refreshed -> " + refreshToken );
		return refreshTokenRepository.save(refreshToken);
	}
	
	public RefreshToken verifyRefreshToken(RefreshToken token) {
		if(token.isRevoked() || !token.getExpiryDate().before(new Date(System.currentTimeMillis()+ expiryTime)) ) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException("Refresh token expired or revoked");
		}
		
		return token;
	}
	
	public void revokeToken(RefreshToken token) {
		token.setRevoked(true);
		refreshTokenRepository.save(token);
	}
}
