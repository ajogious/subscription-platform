package com.ajogious.backend.domain;

import java.math.*;
import java.time.*;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String paystackReference;

    private BigDecimal amount;

    private String status;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime expiresAt;

}
