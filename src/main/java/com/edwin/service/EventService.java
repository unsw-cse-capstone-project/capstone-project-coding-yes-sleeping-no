package com.edwin.service;

import com.edwin.entity.Event;

import java.util.List;

/**
 * Event service interface
 */
public interface EventService {
    List<Event> findAll();

    Event findOne(Integer id);

    void delete(Integer id);

    void save(Event event);

    void update(Event event);

    List<Event> findByType(String type);

    List<Event> findByKeyword(String keyword);

    List<Event> findIndex();
}
