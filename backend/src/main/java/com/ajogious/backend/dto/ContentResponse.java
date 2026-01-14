package com.ajogious.backend.dto;

import java.util.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ContentResponse {

    private UUID id;
    private String title;
    private String body;
    private boolean premium;
    private boolean locked;
}
