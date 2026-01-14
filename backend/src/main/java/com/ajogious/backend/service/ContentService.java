package com.ajogious.backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.ajogious.backend.config.AuthUtil;
import com.ajogious.backend.domain.Content;
import com.ajogious.backend.dto.ContentResponse;
import com.ajogious.backend.repository.ContentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContentService {

        private final ContentRepository contentRepository;
        private final SubscriptionService subscriptionService;

        public List<ContentResponse> getAllContent() {
                UUID userId = AuthUtil.getUserId();
                boolean subscribed = subscriptionService.hasActiveSubscription(userId);

                return contentRepository.findAll().stream()
                                .map(content -> mapContent(content, subscribed))
                                .toList();
        }

        public ContentResponse getContentById(UUID contentId) {
                UUID userId = AuthUtil.getUserId();
                boolean subscribed = subscriptionService.hasActiveSubscription(userId);

                Content content = contentRepository.findById(contentId)
                                .orElseThrow(() -> new RuntimeException("Content not found"));

                if (content.isPremium() && !subscribed) {
                        throw new AccessDeniedException("Premium content locked");
                }

                return mapContent(content, subscribed);
        }

        private ContentResponse mapContent(Content content, boolean subscribed) {
                boolean locked = content.isPremium() && !subscribed;

                return new ContentResponse(
                                content.getId(),
                                content.getTitle(),
                                locked ? null : content.getBody(),
                                content.isPremium(),
                                locked);
        }
}
