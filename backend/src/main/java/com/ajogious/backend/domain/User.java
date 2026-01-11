package com.ajogious.backend.domain;

import java.time.*;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isSubscribed;

    private LocalDateTime subscriptionExpiresAt;

    private LocalDateTime createdAt = LocalDateTime.now();
}
