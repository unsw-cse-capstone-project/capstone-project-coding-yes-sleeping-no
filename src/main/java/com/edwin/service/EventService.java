package com.edwin.service;


import com.edwin.entity.Event;
import com.edwin.entity.User;

import java.util.List;

public interface EventService {
    List<Event> findAll();

    Event findOne(Integer id);

    void delete(Integer id);

    void save(Event event);

    void update(Event event);

    List<Event> findByType(String type);

    List<Event> findByKeyword(String keyword);

    List<Event> findIndex();

//    List<Event> findByRecommended(User user);
}
