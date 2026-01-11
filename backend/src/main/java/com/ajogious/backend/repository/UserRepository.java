package com.ajogious.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajogious.backend.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
