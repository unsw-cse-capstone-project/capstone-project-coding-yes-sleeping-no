package com.edwin.utlis;

import com.edwin.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Random;

/**
 * Create 8-digit unique order number for per order
 */
@CrossOrigin
@Service
@Transactional
public class OrderNumber {

    @Autowired
    private static OrderDao orderDao;

    public static Integer create() {
        int maxAttempt = 10;
        int randomOrderNumber = new Random().nextInt(900000) + 100000;
        System.out.println(randomOrderNumber);
//        Order orderDaoByOrderNumber = orderDao.findByOrderNumber(randomOrderNumber);
//        for (int i = 0; i < maxAttempt; i++) {
//            if (ObjectUtils.isEmpty(orderDaoByOrderNumber)){
//                return randomOrderNumber;
//            }
//        }
        return randomOrderNumber;
//        return -1;
    }
}

