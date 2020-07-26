package com.edwin.dao;

import com.edwin.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Mapper
@Repository
@Service
public interface PaymentDao {
    void save(Payment payment);
}
