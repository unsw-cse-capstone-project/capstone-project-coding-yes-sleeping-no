package com.edwin.dao;

import com.edwin.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Event dao for event table in database
 */
@Mapper
@Repository
@Service
public interface EventDao {
    List<Event> findAll(@Param("status") Integer status);

    Event findOne(@Param("id") Integer id, @Param("status") Integer status);

    void delete(@Param("id") Integer id);

    void save(Event event);

    void update(Event event);

    List<Event> findHost(@Param("user_id") Integer user_id, @Param("status") Integer status);

    List<Event> findType(@Param("type") String type, @Param("status") Integer status);

    List<Event> findKeyword(@Param("title") String title, @Param("type") String type, @Param("description") String description, @Param("status") Integer status);
}
