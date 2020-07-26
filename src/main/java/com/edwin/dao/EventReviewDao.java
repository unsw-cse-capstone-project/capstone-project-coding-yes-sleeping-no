package com.edwin.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Mapper
@Repository
@Service
public interface EventReviewDao {
    void save();
}
