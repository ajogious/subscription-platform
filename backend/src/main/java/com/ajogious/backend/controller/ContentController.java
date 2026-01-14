package com.ajogious.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.ajogious.backend.dto.ContentResponse;
import com.ajogious.backend.service.ContentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public List<ContentResponse> getAllContent() {
        return contentService.getAllContent();
    }

    @GetMapping("/{id}")
    public ContentResponse getContentById(@PathVariable UUID id) {
        return contentService.getContentById(id);
    }
}
