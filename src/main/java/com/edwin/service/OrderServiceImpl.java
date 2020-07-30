package com.edwin.service;

import com.edwin.dao.EventDao;
import com.edwin.dao.OrderDao;
import com.edwin.dao.PaymentDao;
import com.edwin.dao.UserDao;
import com.edwin.entity.Event;
import com.edwin.entity.Order;
import com.edwin.entity.Payment;
import com.edwin.entity.User;
import com.edwin.utlis.Consts;
import com.edwin.utlis.OrderNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * payment status
 * 1 => master card
 * 2 => paypal
 * Description: order service implementation
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private JavaMailSender mailSender;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PaymentDao paymentDao;

    /**
     * Create order
     *
     * @param request
     * @param currentUser
     * @return
     */
    @Override
    public Order create(Map<String, Object> request, User currentUser) {
        Integer eventId = (Integer) request.get("event_id");
        Integer ticketAmount = (Integer) request.get("ticket_amount");
        Order order = new Order();
        Payment payment = new Payment();
        if (eventId == null) {
            throw new RuntimeException("event id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new RuntimeException("current user is empty");
        }
        if (!ObjectUtils.isEmpty(request.get("first_name"))) {
            String firstName = (String) request.get("first_name");
            order.setFirst_name(firstName);
        }
        if (!ObjectUtils.isEmpty(request.get("last_name"))) {
            String lastName = (String) request.get("last_name");
            order.setLast_name(lastName);
        }
        if (!ObjectUtils.isEmpty(request.get("email"))) {
            String email = (String) request.get("email");
            order.setEmail(email);
        }
        if (!ObjectUtils.isEmpty(request.get("phone"))) {
            String phone = (String) request.get("phone");
            order.setPhone(phone);
        }
        if (!ObjectUtils.isEmpty(request.get("address_1"))) {
            String address1 = (String) request.get("address_1");
            order.setAddress_1(address1);
        }
        if (!ObjectUtils.isEmpty(request.get("address_2"))) {
            String address2 = (String) request.get("address_2");
            order.setAddress_2(address2);
        }
        if (!ObjectUtils.isEmpty(request.get("city"))) {
            String city = (String) request.get("city");
            order.setCity(city);
        }
        if (!ObjectUtils.isEmpty(request.get("state"))) {
            String state = (String) request.get("state");
            order.setState(state);
        }
        if (!ObjectUtils.isEmpty(request.get("postcode"))) {
            String postcode = (String) request.get("postcode");
            order.setPostcode(postcode);
        }
        if (!ObjectUtils.isEmpty(request.get("card_number"))) {
            String cardNumber = (String) request.get("card_number");
            payment.setCard_number(cardNumber);
        }
        if (!ObjectUtils.isEmpty(request.get("expiry_date"))) {
            String expiryDate = (String) request.get("expiry_date");
            payment.setExpiry_date(expiryDate);
        }
        if (!ObjectUtils.isEmpty(request.get("cvv"))) {
            Integer cvv = (Integer) request.get("cvv");
            payment.setCvv(cvv);
        }
        if (!ObjectUtils.isEmpty(request.get("status"))) {
            Integer status = (Integer) request.get("status");
            payment.setStatus(status);
        }
        if (!ObjectUtils.isEmpty(request.get("card_holder"))) {
            String cardHolder = (String) request.get("card_holder");
            payment.setCard_holder(cardHolder);
        }
        if (!ObjectUtils.isEmpty(request.get("donate"))) {
            Integer donate = (Integer) request.get("donate");
            order.setDonate(donate);
        }
        Integer userId = currentUser.getId();
        Integer orderNumber = OrderNumber.create();
        if (currentUser.getStatus() != 1) {
            throw new RuntimeException("not customer, cannot booking tickets");
        }
        if (ticketAmount < 1) {
            throw new RuntimeException("ticket amount < 1");
        }
        if (orderNumber == -1) {
            throw new RuntimeException("order number is not unique");
        }
        Event currentEvent = eventDao.findOne(eventId, 1);
        if (ObjectUtils.isEmpty(currentEvent)) {
            throw new RuntimeException("event dose not exist");
        }
        if (currentEvent.getAvailable_tickets() - ticketAmount < 0) {
            throw new RuntimeException("fail to pay, because of the short of available tickets");
        }
        if (new Date().getTime() - currentEvent.getStart_date().getTime() > 0) {
            throw new RuntimeException("event is over, fail to pay");
        }
        BigDecimal ticketPrice = currentEvent.getTicket_price();
        order.setOrder_number(orderNumber);
        order.setEvent_id(eventId);
        order.setUser_id(userId);
        order.setTicket_amount(ticketAmount);
        order.setStatus(1);
        BigDecimal totalPrice = getTotalPrice(ticketPrice, ticketAmount);
        System.out.println(ticketPrice);
        Integer available_tickets = currentEvent.getAvailable_tickets();
        currentEvent.setAvailable_tickets(available_tickets - ticketAmount);
        currentEvent.setUpdated_at(new Date());
        eventDao.update(currentEvent);
        order.setTotal_price(totalPrice);
        order.setCreated_at(new Date());
        order.setUpdated_at(new Date());
        payment.setAccount_balance(new BigDecimal(0.00));
        payment.setCreated_at(new Date());
        payment.setUpdated_at(new Date());
        Integer hostId = currentEvent.getUser_id();
        User host = userDao.findByUserId(hostId);
        host.setUser_balance(host.getUser_balance().add(ticketPrice));
        try {
            userDao.update(host);
            orderDao.save(order);
            paymentDao.save(payment);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("719204145@qq.com");
            message.setTo(currentUser.getEmail());
            message.setSubject("CYSN - order receipt");
            message.setText("   The event: " + currentEvent.getTitle() + " has been ordered successfully." + "\n" + "\n" +
                    "   Your order number:  " + orderNumber.toString() + "\n" + "\n" +
                    "   Ticket amount:  "
                    + ticketAmount.toString() + "\n" + "\n" +
                    "   Total price:  "
                    + totalPrice.toString() +
                    "\n" + "\n" + "   Thanks for your support to CYSN website." + "\n" + "\n" +
                    "   See more exciting activities, please go to the home page! ");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Order orderByOrderNumber = orderDao.findByOrderNumber(orderNumber);
        return orderByOrderNumber;
    }

    /**
     * Customer confirms payment for one order
     *
     * @param orderId
     * @param currentUser
     * @return
     */
    @Override
    public Order update(Integer orderId, User currentUser) {
        if (orderId == null) {
            throw new RuntimeException("order id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new RuntimeException("current user is empty");
        }
        Order currentOrder = orderDao.findOne(orderId);
        if (ObjectUtils.isEmpty(currentOrder)) {
            throw new RuntimeException("current order is empty");
        }
        Date created_at = currentOrder.getCreated_at();
        if (ObjectUtils.isEmpty(created_at)) {
            throw new RuntimeException("create at timestamp is empty");
        }
        long timeDifference = new Date().getTime() - created_at.getTime();
        if (timeDifference / (1000 * 60) > 10) {
            throw new RuntimeException("more than 10 minutes, order expires");
        }
        Event currentEvent = eventDao.findOne(currentOrder.getEvent_id(), 1);
        if (currentEvent.getAvailable_tickets() - currentOrder.getTicket_amount() < 0) {
            throw new RuntimeException("fail to pay, because of the short of available tickets");
        }
        if (new Date().getTime() - currentEvent.getEnd_time().getTime() <= 0) {
            throw new RuntimeException("event is over, fail to pay");
        }
        currentOrder.setStatus(1);
        currentOrder.setUpdated_at(new Date());
        orderDao.update(currentOrder);
        currentEvent.setAvailable_tickets(currentEvent.getAvailable_tickets() - currentOrder.getTicket_amount());
        currentEvent.setUpdated_at(new Date());
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

    /**
     * Get all orders by customer id
     *
     * @param userId
     * @return
     */
    @Override
    public List<Order> getAll(Integer userId) {
        if (userId == null) {
            throw new RuntimeException("user id is empty");
        }
        List<Order> allOrders = orderDao.findAll(userId);
        if (allOrders.size() == 0) {
            throw new RuntimeException("there is no order related");
        }
        return allOrders;
    }

    /**
     * Cancel one order by customer
     *
     * @param orderId
     * @param currentUser
     * @return
     */
    @Override
    public Order cancel(Integer orderId, User currentUser) {
        if (orderId == null) {
            throw new RuntimeException("order id is empty");
        }
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new RuntimeException("current user is empty");
        }
        Order currentOrder = orderDao.findOne(orderId);
        if (currentOrder.getStatus() != 1) {
            throw new RuntimeException("order status is not 1, cannot cancel");
        }
        Event currentEvent = eventDao.findOne(currentOrder.getEvent_id(), 1);
        if (currentEvent == null) {
            throw new RuntimeException("corresponding event dose not exist");
        }
        long timeDifference = currentEvent.getStart_date().getTime() - new Date().getTime();
        if (timeDifference / (1000 * 60 * 60 * 24) < 7) {
            throw new RuntimeException("less than seven days from the start date, cannot cancel");
        }
        BigDecimal totalPrice = currentOrder.getTotal_price();
        Integer availableTickets = currentEvent.getAvailable_tickets();
        currentOrder.setUpdated_at(new Date());
        currentOrder.setStatus(2);
        currentEvent.setAvailable_tickets(availableTickets + currentOrder.getTicket_amount());
        currentEvent.setUpdated_at(new Date());
        Integer hostId = currentEvent.getUser_id();
        User host = userDao.findByUserId(hostId);
        host.setUser_balance(host.getUser_balance().subtract(totalPrice));
        BigDecimal userBalance = currentUser.getUser_balance();
        BigDecimal newUserBalance = userBalance.add(totalPrice);
        currentUser.setUser_balance(newUserBalance);
        currentUser.setUpdated_at(new Date());
        try {
            eventDao.update(currentEvent);
            orderDao.update(currentOrder);
            userDao.update(currentUser);
            userDao.update(host);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Consts.SENDER_EMAIL);
            message.setTo(currentUser.getEmail());
            message.setSubject("CYSN - cancel order notification");
            message.setText("   The event you ordered: " + currentEvent.getTitle() + " has been canceled successfully." + "\n" + "\n" +
                    "   CYSN staff will refund booking costs to your " +
                    "user balance within 3 business days." + "\n" + "\n" + "   Thanks for your support to CYSN website." + "\n" + "\n" +
                    "   See more exciting activities, please go to the home page! ");
            mailSender.send(message);
            return currentOrder;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Get all orders related to events created by host
     *
     * @param currentUser
     * @return
     */
    @Override
    public List<Order> findHost(User currentUser) {
        if (ObjectUtils.isEmpty(currentUser)) {
            throw new RuntimeException("current user is empty");
        }
        Integer userId = currentUser.getId();
        List<Order> orders = new ArrayList<>();
        List<Event> eventsByHost = eventDao.findHost(userId, 1);
        if (eventsByHost.size() < 0) {
            throw new RuntimeException("no event created by host");
        }
        if (eventsByHost.size() == 1) {
            List<Order> orderDaoByEventId = orderDao.findByEventId(eventsByHost.get(0).getId());
            if (orderDaoByEventId.size() <= 0) {
                throw new RuntimeException("no order booked by customers");
            }
            return orderDaoByEventId;
        } else {
            for (Event event : eventsByHost) {
                List<Order> orderDaoByEventId = orderDao.findByEventId(event.getId());
                if (orderDaoByEventId.size() <= 0) {
                    continue;
                } else if (orderDaoByEventId.size() == 1) {
                    orders.add(orderDaoByEventId.get(0));
                } else {
                    for (Order order : orderDaoByEventId) {
                        orders.add(order);
                    }
                }
            }
        }
        return orders;
    }

    /**
     * Calculate total price of per order
     *
     * @param ticketPrice
     * @param ticketAmount
     * @return
     */
    private BigDecimal getTotalPrice(BigDecimal ticketPrice, Integer ticketAmount) {
        BigDecimal totalPrice = ticketPrice.multiply(new BigDecimal(ticketAmount.toString()));
        return totalPrice;
    }
}
