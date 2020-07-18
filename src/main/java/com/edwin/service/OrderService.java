package com.edwin.service;

import com.edwin.entity.Order;
import com.edwin.entity.User;

import java.util.List;

public interface OrderService {
    Order create(Integer eventId, Integer ticketAmount, User currentUser);
    Order update(Integer orderId, User currentUser);
    List<Order> getAll(Integer userId);
    Order cancel(Integer orderId, User currentUser);
}
