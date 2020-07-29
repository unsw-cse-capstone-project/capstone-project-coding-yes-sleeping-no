package com.edwin.dao;

import com.edwin.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order dao for order table in database
 */
@Mapper
@Repository
@Service
public interface OrderDao {
    Order findByOrderNumber(Integer orderNumber);

    void save(Order order);

    Order findOne(Integer id);

    void update(Order order);

    List<Order> findAll(Integer user_id);

    List<Order> findByHost(@Param("user_id")Integer user_id,@Param("event_id") Integer event_id);

    List<Order> findByEventId(Integer event_id);
}
