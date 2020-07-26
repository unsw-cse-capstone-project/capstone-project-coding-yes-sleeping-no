package com.edwin.service;

import com.edwin.dao.EventReviewDao;
import com.edwin.entity.EventReview;
import com.edwin.entity.User;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
@Service
@Transactional
public class EventReviewServiceImpl implements EventReviewService{

    @Autowired
    private EventReviewDao eventReviewDao;

    @Override
    public void sendReview(Map<String, Object> map1, User currentUser) {
        String content =(String) map1.get("content");
        Integer eventId =(Integer) map1.get("eventId");
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
