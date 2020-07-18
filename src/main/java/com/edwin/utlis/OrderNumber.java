package com.edwin.utlis;

import com.edwin.dao.OrderDao;
import com.edwin.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Random;

public class OrderNumber {

    @Autowired
    private static OrderDao orderDao;

    public static Integer create(){
        int maxAttempt = 10;
        int randomOrderNumber = new Random().nextInt(900000) + 100000;
        Order orderDaoByOrderNumber = orderDao.findByOrderNumber(randomOrderNumber);
        for (int i = 0; i < maxAttempt; i++) {
            if (ObjectUtils.isEmpty(orderDaoByOrderNumber)){
                return randomOrderNumber;
            }
        }
        return -1;
    }
}

