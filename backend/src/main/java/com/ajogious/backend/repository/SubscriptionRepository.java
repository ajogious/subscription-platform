package com.ajogious.backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajogious.backend.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}