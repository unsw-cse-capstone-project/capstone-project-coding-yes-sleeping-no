package com.edwin.dao;


import com.edwin.entity.EventReview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EventReview dao for event_review table in database
 */
@Mapper
@Repository
@Service
public interface EventReviewDao {
    void save(EventReview eventReview);

    List<EventReview> findByEvent(Integer event_id);
}
