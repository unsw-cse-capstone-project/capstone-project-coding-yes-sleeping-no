package com.edwin.service;

import com.edwin.dao.EventDao;
import com.edwin.dao.OrderDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.utlis.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Description: event service implementation
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Value("${photo.dir}")
    private String realPath;

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    /**
     * Find all events
     *
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Event> findAll() {
        return eventDao.findAll(1);
    }

    /**
     * Find one event by event id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Event findOne(Integer id) {
        return eventDao.findOne(id, 1);
    }

    /**
     * Delete one event by host
     *
     * @param id
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("event id does not exist");
        }
        Event currentEvent = eventDao.findOne(id, 1);
        if (currentEvent.getStatus() != 1) {
            throw new RuntimeException("event has been deleted");
        }
        long timeDifference = new Date().getTime() - currentEvent.getStart_date().getTime();
        if (timeDifference >= 0) {
            throw new RuntimeException("event has started, cannot cancel");
        }
        List<Order> ordersByEventId = orderDao.findByEventId(id);
        Iterator<Order> iterator = ordersByEventId.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getStatus() != 1) {
                iterator.remove();
            }
        }
//        if (ordersByEventId.size() == 1) {
//            if (ordersByEventId.get(0).getStatus() != 1) {
//                ordersByEventId.remove(ordersByEventId.get(0));
//            }
//        } else if (ordersByEventId.size() > 1) {
//            ordersByEventId.removeIf(order -> order.getStatus() != 1);
//        }
        ArrayList<Integer> usersId = new ArrayList<>();
        ArrayList<BigDecimal> ticketAmounts = new ArrayList<>();
        if (ordersByEventId.size() == 1) {
            Integer userId = ordersByEventId.get(0).getUser_id();
            BigDecimal ticketAmount = ordersByEventId.get(0).getTotal_price();
            ticketAmounts.add(ticketAmount);
            usersId.add(userId);
            ordersByEventId.get(0).setStatus(2);
            ordersByEventId.get(0).setUpdated_at(new Date());
            orderDao.update(ordersByEventId.get(0));
        } else if (ordersByEventId.size() > 1) {
            for (Order order : ordersByEventId) {
                Integer userId = order.getUser_id();
                BigDecimal ticketAmount = order.getTotal_price();
                ticketAmounts.add(ticketAmount);
                usersId.add(userId);
                order.setStatus(2);
                order.setUpdated_at(new Date());
                orderDao.update(order);
            }
        }
        for (int i = 0; i < usersId.size(); i++) {
            Integer userId = usersId.get(i);
            BigDecimal refund = ticketAmounts.get(i);
            User userByUserId = userDao.findByUserId(userId);
            userByUserId.setUser_balance(userByUserId.getUser_balance().add(refund));
            userByUserId.setUpdated_at(new Date());
            userDao.update(userByUserId);
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
        currentEvent.setStatus(0);
        eventDao.update(currentEvent);
    }

    /**
     * Create one event by host
     *
     * @param event
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(Event event) {
        String base64Data = event.getCover_image();
        String dataPrix = "";
        String data = "";
        if (base64Data == null || "".equals(base64Data)) {
            throw new RuntimeException("image is empty");
        } else {
            String[] d = base64Data.split("base64,");
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                throw new RuntimeException("image format is invalid");
            }
        }
        String suffix = "";
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            suffix = ".png";
        } else {
            throw new RuntimeException("image extension is invalid");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName = uuid + suffix;
        String imgFilePath = realPath + tempFileName;
        Base64.Decoder decoder = Base64.getDecoder();
        event.setCover_image(imgFilePath);
        try {
            byte[] b = decoder.decode(data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            eventDao.save(event);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update event information by host
     *
     * @param event
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update(Event event) {
        eventDao.update(event);
    }

    /**
     * Find events by type
     *
     * @param type
     * @return
     */
    @Override
    public List<Event> findByType(String type) {
        if (StringUtils.isEmpty(type)) {
            throw new RuntimeException("type is empty");
        }
        List<Event> eventsByType = eventDao.findType(type, 1);
        if (eventsByType.size() == 0) {
            throw new RuntimeException("event by type is empty");
        } else {
            return eventsByType;
        }
    }

    /**
     * Find events by keyword (search)
     *
     * @param keyword
     * @return
     */
    @Override
    public List<Event> findByKeyword(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            throw new RuntimeException("keyword is empty");
        }
        String title = "%" + keyword + "%";
        String type = "%" + keyword + "%";
        String description = "%" + keyword + "%";
        List<Event> eventsByKeyword = eventDao.findKeyword(title, type, description, 1);
        return eventsByKeyword;
    }

    /**
     * Get all events for index page
     *
     * @return
     */
    @Deprecated
    @Override
    public List<Event> findIndex() {
        List<Event> allEvents = eventDao.findAll(1);
        Iterator<Event> iterator = allEvents.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            Date start_date = event.getStart_date();
            Long timeDifference = start_date.getTime() - new Date().getTime();
            int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
            if (days < 15) {
                iterator.remove();
            } else if (event.getAvailable_tickets() <= 1) {
                iterator.remove();
            }
        }
//        for (Event event : tmp) {
//            Date start_date = event.getStart_date();
//            Long timeDifference = start_date.getTime() - new Date().getTime();
//            int days = (int) (timeDifference / (1000 * 60 * 60 * 24));
//            if (days < 15) {
//                allEvents.remove(event);
//            }
//            if (event.getAvailable_tickets() <= 1) {
//                allEvents.remove(event);
//            }
//        }
        return allEvents;
    }
}
