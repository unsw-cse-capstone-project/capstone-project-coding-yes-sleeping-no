package com.edwin.dao;


import com.edwin.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
@Service
public interface EventDao {
    List<Event> findAll();

    Event findOne(Integer id);

    void delete(Integer id);

    void save(Event event);

    void update(Event event);

    List<Event> findHost(Integer user_id);
}
