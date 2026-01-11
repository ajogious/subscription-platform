package com.ajogious.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajogious.backend.domain.Content;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
