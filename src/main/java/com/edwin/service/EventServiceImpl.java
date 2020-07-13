package com.edwin.service;

import com.edwin.dao.EventDao;
import com.edwin.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Event findOne(Integer id) {
        return eventDao.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
        eventDao.delete(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void save(Event event) {
        eventDao.save(event);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void update(Event event) {
        eventDao.update(event);
    }
}
