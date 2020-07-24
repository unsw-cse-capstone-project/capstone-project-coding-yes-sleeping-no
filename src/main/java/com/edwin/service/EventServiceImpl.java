package com.edwin.service;

import com.edwin.dao.EventDao;
import com.edwin.dao.OrderDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.utlis.Consts;
import com.edwin.utlis.StringUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Event findOne(Integer id) {
        return eventDao.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("event id does not exist");
        }
        Event currentEvent = eventDao.findOne(id);
        long timeDifference = new Date().getTime() - currentEvent.getStart_date().getTime();
        if (timeDifference >= 0) {
            throw new RuntimeException("event has started, cannot cancel");
        }
        List<Order> ordersByEventId = orderDao.findByEventId(id);
        ArrayList<Integer> usersId = new ArrayList<>();
        ArrayList<BigDecimal> ticketAmounts = new ArrayList<>();
        for (Order order : ordersByEventId) {
            Integer userId = order.getUser_id();
            BigDecimal ticketAmount = order.getTotal_price();
            ticketAmounts.add(ticketAmount);
            usersId.add(userId);
            order.setStatus(2);
            order.setUpdated_at(new Date());
        }
        // 异步处理
        for (int i = 0; i < usersId.size(); i++) {
            Integer userId = usersId.get(i);
            BigDecimal refund = ticketAmounts.get(i);
            User userByUserId = userDao.findByUserId(userId);
            userByUserId.setUser_balance(userByUserId.getUser_balance().add(refund));
            userByUserId.setUpdated_at(new Date());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Consts.SENDER_EMAIL);
            message.setTo(userByUserId.getEmail());
            message.setSubject("CYSN - cancel event notification");
            message.setText("   Unfortunately, The event you ordered: " + currentEvent.getTitle() + " has been canceled by host." + "\n" + "\n" +
                    "   CYSN staff will refund booking costs to your " +
                    "user balance within 3 business days." + "\n" + "\n" + "   Thanks for your support to CYSN website." + "\n" + "\n" +
                    "   See more exciting activities, please go to the home page! ");
            mailSender.send(message);
        }
        eventDao.delete(id);
    }

    @Override
    //tbd validation format
    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(Event event) {
        eventDao.save(event);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update(Event event) {
        eventDao.update(event);
    }

    @Override
    public List<Event> findByType(String type) {
        if (StringUtils.isEmpty(type)){
            throw new RuntimeException("type is empty");
        }
        List<Event> eventsByType = eventDao.findType(type);
        if (eventsByType.size() == 0) {
            throw new RuntimeException("Event by type is empty");
        } else {
            return eventsByType;
        }
    }

    @Override
    public List<Event> findByKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)){
            throw new RuntimeException("keyword is empty");
        }
        String title = "%" + keyword + "%";
        String type = "%" + keyword + "%";
        String description = "%" + keyword + "%";
        List<Event> eventsByKeyword = eventDao.findKeyword(title,type,description);
        return eventsByKeyword;
    }

    @Override
    public List<Event> findIndex() {
        List<Event> allEvents = eventDao.findAll();
        for (Event event: allEvents){
            Date start_date = event.getStart_date();
            Long timeDiffernce = start_date.getTime() - new Date().getTime();
            int days = (int) (timeDiffernce / (1000 * 60 * 60 * 24));
            if (days < 15){
                allEvents.remove(event);
            }
            if (event.getAvailable_tickets() <= 1){
                allEvents.remove(event);
            }
        }
        return allEvents;
    }
}
