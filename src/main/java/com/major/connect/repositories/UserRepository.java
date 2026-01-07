package com.major.connect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.major.connect.models.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

	Optional<UserInfo>findByUsername(String username);
	
	boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
