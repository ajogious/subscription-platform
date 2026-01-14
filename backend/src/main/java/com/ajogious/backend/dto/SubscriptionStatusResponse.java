package com.ajogious.backend.dto;

import java.time.LocalDateTime;

public record SubscriptionStatusResponse(
        boolean subscribed,
        LocalDateTime expiresAt,
        String status) {
}
