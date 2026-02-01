package com.major.connect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.connect.models.RefreshToken;
import com.major.connect.models.UserInfo;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByToken(String token);
	Optional<RefreshToken> findByUser(UserInfo user);
	void deleteByUser(UserInfo user);
}
