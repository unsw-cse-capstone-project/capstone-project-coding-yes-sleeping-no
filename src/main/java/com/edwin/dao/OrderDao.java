package com.edwin.dao;

import com.edwin.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
@Service
public interface OrderDao {
    Order findByOrderNumber(Integer orderNumber);
    void  save(Order order);
    Order findOne(Integer id);
    void update(Integer id);
    List<Order> findAll(Integer user_id);
    List<Order> findByHost(Integer user_id, Integer event_id);
    List<Order> findByEventId(Integer event_id);
}
