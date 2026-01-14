package com.ajogious.backend.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajogious.backend.config.AuthUtil;
import com.ajogious.backend.domain.Subscription;
import com.ajogious.backend.dto.SubscriptionStatusResponse;
import com.ajogious.backend.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
public class MeController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/subscription")
    public SubscriptionStatusResponse mySubscription() {
        UUID userId = AuthUtil.getUserId();

        Subscription sub = subscriptionService.getActiveSubscription(userId);

        if (sub == null) {
            return new SubscriptionStatusResponse(false, null, "NONE");
        }

        return new SubscriptionStatusResponse(
                true,
                sub.getExpiresAt(),
                "ACTIVE");
    }
}
