package com.ajogious.backend.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajogious.backend.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paystack")
@RequiredArgsConstructor
public class PaystackWebhookController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {

        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        String reference = (String) data.get("reference");
        String status = (String) data.get("status");

        if ("success".equals(status)) {
            subscriptionService.confirmPayment(reference);
        }

        return ResponseEntity.ok("Webhook received");
    }
}
