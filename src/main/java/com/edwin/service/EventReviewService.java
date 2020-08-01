package com.edwin.service;

import com.edwin.entity.User;

import java.util.Map;

/**
 * Event review service interface
 */
public interface EventReviewService {
    void sendReview(Map<String, Object> map1, User currentUser);

    void reply(Map<String, Object> request, User currentUser);
}
