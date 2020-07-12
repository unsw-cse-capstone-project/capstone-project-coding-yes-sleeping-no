package com.edwin.service;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.DecimalFormat;
import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        User userByName = userDao.findByUserName(user.getUsername());
        if (userByName == null) {
        user.setStatus(1);
        user.setCreated_at(new Date());
        user.setUser_balance(0);
        user.setUpdate_at(new Date());
        userDao.save(user);
        } else {
            throw new RuntimeException("User exists !");
        }
    }

    @Override
    public User login(User user) {
        User userByName = userDao.findByUserName(user.getUsername());
        if (!ObjectUtils.isEmpty(userByName)) {
            if (userByName.getPassword().equals(user.getPassword())) {
                return userByName;
            } else {
                throw new RuntimeException("Password is not correct !");
            }
        } else {
            throw new RuntimeException("Username is not correct !");
        }
    }
}
