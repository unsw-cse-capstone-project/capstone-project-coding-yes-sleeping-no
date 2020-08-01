package com.edwin.dao;

import com.edwin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * User dao for user table in database
 */
@Mapper
@Repository
@Service
public interface UserDao {

    void save(User user);

    User findByUserName(String username);

    User findByUserEmail(String email);

    User findByUserId(Integer id);

    void update(User user);
}
