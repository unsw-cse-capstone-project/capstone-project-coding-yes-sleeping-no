package com.edwin.service;

import com.edwin.entity.Order;
import com.edwin.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Order service interface
 */
public interface OrderService {
    Order create(Map<String, Object> map, User currentUser);

    Order update(Integer orderId, User currentUser);

    List<Order> getAll(Integer userId);

    Order cancel(Integer orderId, User currentUser);

    List<Order> findHost(User currentUser);
}
