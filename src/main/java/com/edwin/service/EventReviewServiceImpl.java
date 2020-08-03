package com.edwin.service;

import com.edwin.dao.EventDao;
import com.edwin.dao.EventReviewDao;
import com.edwin.dao.OrderDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.EventReview;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description: event review service implementation
 */
@Service
@Transactional
public class EventReviewServiceImpl implements EventReviewService {

    @Autowired
    private EventReviewDao eventReviewDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    /**
     * Customer sends review to one past event
     *
     * @param request
     * @param currentUser
     */
    @Override
    public void sendReview(Map<String, Object> request, User currentUser) {
        if (currentUser == null) {
            throw new RuntimeException("current user is empty");
        }
        String content = (String) request.get("content");
        Integer eventId = (Integer) request.get("eventId");
        Integer senderId = currentUser.getId();
        Event currentEvent = eventDao.findOne(eventId, 1);
        if (currentEvent == null) {
            throw new RuntimeException("no event found");
        }
        Date startDate = currentEvent.getStart_date();
        Long timeDifference = new Date().getTime() - startDate.getTime();
        int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
        if (days <= 0) {
            throw new RuntimeException("event is not over, cannot send event review");
        }
        List<Order> orders = orderDao.findByHost(currentUser.getId(), eventId);
        if (orders.size() == 0) {
            throw new RuntimeException("not book this event before, fail to send event review");
        }
        if (orders.size() == 1) {
            if (orders.get(0).getStatus() == 2) {
                throw new RuntimeException("cancel order before, fail to send event review");
            }
        }
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getStatus() == 2) {
                iterator.remove();
            }
        }
        if (orders.size() <= 0) {
            throw new RuntimeException("cancel order before, fail to send event review");
        }
        EventReview eventReview = new EventReview();
        eventReview.setContent(content);
        eventReview.setSender_id(senderId);
        eventReview.setReceiver_id(0);
        eventReview.setEvent_id(eventId);
        eventReview.setCreated_at(new Date());
        eventReview.setUpdated_at(new Date());
        eventReviewDao.save(eventReview);
    }

    /**
     * Host reply to customer's event review
     *
     * @param request
     * @param currentUser
     */
    @Override
    public void reply(Map<String, Object> request, User currentUser) {
        if (currentUser == null) {
            throw new RuntimeException("current user is empty");
        }
        Integer reviewId = (Integer) request.get("reviewId");
        String content = (String) request.get("content");
//        Integer eventId = (Integer) request.get("eventId");
//        Integer receiverId = (Integer) request.get("userId");
        Integer senderId = currentUser.getId();
        EventReview review = eventReviewDao.findOne(reviewId);
        if (review == null){
            throw new RuntimeException("no event review found");
        }
        Event currentEvent = eventDao.findOne(review.getEvent_id(), 1);
        if (currentEvent == null) {
            throw new RuntimeException("no event found");
        }
        if (currentUser == null) {
            throw new RuntimeException("no user found");
        }
        Date startDate = currentEvent.getStart_date();
        Long timeDifference = new Date().getTime() - startDate.getTime();
        int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
        if (days <= 0) {
            throw new RuntimeException("event is not over, cannot send event review");
        }
        if (currentEvent.getUser_id() != currentUser.getId()) {
            throw new RuntimeException("not host, cannot reply to customer");
        }
        EventReview eventReview = new EventReview();
        eventReview.setContent(content);
        eventReview.setSender_id(senderId);
        eventReview.setReceiver_id(review.getSender_id());
        eventReview.setEvent_id(review.getEvent_id());
        eventReview.setCreated_at(new Date());
        eventReview.setUpdated_at(new Date());
        eventReviewDao.save(eventReview);
    }
}
