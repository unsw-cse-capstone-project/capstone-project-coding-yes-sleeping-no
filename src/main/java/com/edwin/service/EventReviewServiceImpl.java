package com.edwin.service;

import com.edwin.dao.EventReviewDao;
import com.edwin.entity.EventReview;
import com.edwin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Description: event review service implementation
 */
@Service
@Transactional
public class EventReviewServiceImpl implements EventReviewService {

    @Autowired
    private EventReviewDao eventReviewDao;

    /**
     * Customer sends review to one past event
     *
     * @param request
     * @param currentUser
     */
    @Override
    public void sendReview(Map<String, Object> request, User currentUser) {
        String content = (String) request.get("content");
        Integer eventId = (Integer) request.get("eventId");
        Integer senderId = currentUser.getId();
        EventReview eventReview = new EventReview();
        eventReview.setContent(content);
        eventReview.setSender_id(senderId);
        eventReview.setEvent_id(eventId);
        eventReview.setCreated_at(new Date());
        eventReview.setUpdated_at(new Date());
        eventReviewDao.save(eventReview);
    }
}
