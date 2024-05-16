package com.pdv.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pdv.oauth.model.GoogleUser;

@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleUser, Integer> {
    Optional<GoogleUser> findByEmail(String email);
}
