package com.edwin.service;



import com.edwin.dao.EventDao;
import com.edwin.dao.OrderDao;
import com.edwin.entity.Event;
import com.edwin.entity.Order;
import com.edwin.entity.User;
import com.edwin.utlis.OrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order create(Integer eventId, Integer ticketAmount, User currentUser) {
        if (eventId == null){
            throw new RuntimeException("event id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)){
            throw new RuntimeException("current user is empty");
        }
        Integer userId = currentUser.getId();
        Integer orderNumber = OrderNumber.create();
        if (orderNumber == -1){
            throw new RuntimeException("order number is not unique");
        }
        Event currentEvent = eventDao.findOne(eventId);
        if (ObjectUtils.isEmpty(currentEvent)){
            throw new RuntimeException("event dose not exist");
        }
        BigDecimal ticketPrice = currentEvent.getTicket_price();
        Order order = new Order();
        order.setOrder_number(orderNumber);
        order.setEvent_id(eventId);
        order.setUser_id(userId);
        order.setTicket_amount(ticketAmount);
        order.setStatus(0);
        System.out.println(getTotalPrice(ticketPrice, ticketAmount));
        order.setTotal_price(getTotalPrice(ticketPrice, ticketAmount));
        order.setCreated_at(new Date());
        order.setUpdated_at(new Date());
        try {
            orderDao.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order orderByOrderNumber = orderDao.findByOrderNumber(orderNumber);
        return orderByOrderNumber;
    }

    @Override
    public Order update(Integer orderId, User currentUser) {
        if (orderId == null){
            throw new RuntimeException("order id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)){
            throw new RuntimeException("current user is empty");
        }
        Order currentOrder = orderDao.findOne(orderId);
        if (ObjectUtils.isEmpty(currentOrder)){
            throw new RuntimeException("current order is empty");
        }
        Date created_at = currentOrder.getCreated_at();
        long timeDifference = new Date().getTime() - created_at.getTime();
        if (timeDifference/(1000 * 60) > 10){
            throw new RuntimeException("more than 10 minutes, order expires");
        }
        Event currentEvent = eventDao.findOne(currentOrder.getEvent_id());
        if (currentEvent.getAvailable_tickets() - currentOrder.getTicket_amount() < 0){
            throw new RuntimeException("fail to pay, because of the short of available tickets");
        }
        if ( new Date().getTime() - currentEvent.getEnd_date().getTime() <= 0){
            throw new RuntimeException("event is over, fail to pay");
        }
        // TBD payment process
        // user has paid
        currentOrder.setStatus(1);
        currentOrder.setUpdated_at(new Date());
        orderDao.update(currentOrder.getId());
        currentEvent.setAvailable_tickets(currentEvent.getAvailable_tickets() - currentOrder.getTicket_amount());
        currentEvent.setUpdate_at(new Date());
        eventDao.update(currentEvent);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("719204145@qq.com");
        message.setTo(currentUser.getEmail());
        message.setSubject("CYSN - order receipt");
        message.setText("Your order number: " + currentOrder.getOrder_number() +
                "\n" + "ticket amount: " + currentOrder.getTicket_amount() +
                "\n" + "total price: " + currentOrder.getTotal_price());
        mailSender.send(message);
        return currentOrder;
    }

    @Override
    public List<Order> getAll(Integer userId) {
        if (userId == null){
            throw new RuntimeException("user id is empty");
        }
        List<Order> allOrders = orderDao.findAll(userId);
        if (allOrders.isEmpty()){
            throw new RuntimeException("there is no order related");
        }
        return allOrders;
    }

    @Override
    public Order cancel(Integer orderId, User currentUser) {
        if (orderId == null){
            throw new RuntimeException("order id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)){
            throw new RuntimeException("current user is empty");
        }
        Order currentOrder = orderDao.findOne(orderId);
        if (currentOrder.getStatus() != 1){
            throw new RuntimeException("order status is not 1, cannot cancel");
        }
        Event currentEvent = eventDao.findOne(currentOrder.getEvent_id());
        if (new Date().getTime() - currentEvent.getStart_date().getTime() >= 0){
            throw new RuntimeException("event has started, cannot cancel");
        }
        BigDecimal totalPrice = currentOrder.getTotal_price();
        Integer availableTickets = currentEvent.getAvailable_tickets();
        currentOrder.setUpdated_at(new Date());
        currentOrder.setStatus(2);
        currentEvent.setAvailable_tickets(availableTickets + currentOrder.getTicket_amount());
        currentEvent.setUpdate_at(new Date());
        BigDecimal userBalance = currentUser.getUser_balance();
        BigDecimal newUserBalance = userBalance.add(totalPrice);
        currentUser.setUser_balance(newUserBalance);
        currentUser.setUpdated_at(new Date());
        return currentOrder;
    }

    // TBD precision problems
    private BigDecimal getTotalPrice(BigDecimal ticketPrice, Integer ticketAmount){
        BigDecimal totalPrice = ticketPrice.multiply(new BigDecimal(ticketAmount.toString()));
        return totalPrice;
    }
}
