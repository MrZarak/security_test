package com.zarak.spring_security_test.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zarak.spring_security_test.jpa.model.UserJPA;

@Repository
public interface UserJPARepository extends JpaRepository<UserJPA, Long> {
	Optional<UserJPA> findByEmail(String email);
	Optional<UserJPA> findByUsername(String username);
}
