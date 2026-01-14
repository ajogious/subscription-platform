package com.ajogious.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ajogious.backend.domain.Subscription;
import com.ajogious.backend.domain.User;
import com.ajogious.backend.repository.SubscriptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    /**
     * Step 1: Initialize subscription (before payment)
     */
    public Map<String, Object> initializeSubscription(User user) {

        String reference = UUID.randomUUID().toString();

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPaystackReference(reference);
        subscription.setAmount(BigDecimal.valueOf(5000));
        subscription.setStatus("PENDING");

        subscriptionRepository.save(subscription);

        return Map.of(
                "reference", reference,
                "amount", 5000,
                "email", user.getEmail());
    }

    /**
     * Step 2: Confirm payment from Paystack webhook
     */
    public void confirmPayment(String reference) {

        Subscription subscription = subscriptionRepository
                .findByPaystackReference(reference)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        if ("SUCCESS".equals(subscription.getStatus())) {
            return;
        }

        subscription.setStatus("SUCCESS");
        subscription.setExpiresAt(LocalDateTime.now().plusDays(30));

        subscriptionRepository.save(subscription);
    }

    /**
     * Used by content gating logic
     */
    public boolean hasActiveSubscription(UUID userId) {
        return subscriptionRepository
                .findActiveSubscription(userId, LocalDateTime.now())
                .isPresent();
    }

    /**
     * Used by /me/subscription endpoint
     */
    public Subscription getActiveSubscription(UUID userId) {
        return subscriptionRepository
                .findActiveSubscription(userId, LocalDateTime.now())
                .orElse(null);
    }
}
