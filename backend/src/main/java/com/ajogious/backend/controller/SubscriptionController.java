package com.ajogious.backend.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajogious.backend.domain.User;
import com.ajogious.backend.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public Map<String, Object> subscribe(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        return subscriptionService.initializeSubscription(user);
    }
}
