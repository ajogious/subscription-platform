package com.ajogious.backend.repository;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajogious.backend.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    @Query("""
                SELECT s FROM Subscription s
                WHERE s.user.id = :userId
                  AND s.status = 'SUCCESS'
                  AND s.expiresAt > :now
            """)
    Optional<Subscription> findActiveSubscription(UUID userId, LocalDateTime now);

    Optional<Subscription> findByPaystackReference(String reference);

    Optional<Subscription> findTopByUserIdAndStatusOrderByCreatedAtDesc(UUID userId, String string);
}