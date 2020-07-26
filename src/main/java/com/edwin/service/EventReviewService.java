package com.edwin.service;

import com.edwin.entity.User;

import java.util.Map;

public interface EventReviewService {
    void sendReview(Map<String, Object> map1, User currentUser);
}
