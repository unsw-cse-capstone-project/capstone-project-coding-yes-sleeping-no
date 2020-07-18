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
    Order findOne(Integer orderId);
    void update(Integer orderId);
    List<Order> findAll(Integer userId);
}
