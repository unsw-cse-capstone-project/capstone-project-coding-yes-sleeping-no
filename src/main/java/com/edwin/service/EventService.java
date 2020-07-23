package com.edwin.service;


import com.edwin.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> findAll();

    Event findOne(Integer id);

    void delete(Integer id);

    void save(Event event);

    void update(Event event);
}
