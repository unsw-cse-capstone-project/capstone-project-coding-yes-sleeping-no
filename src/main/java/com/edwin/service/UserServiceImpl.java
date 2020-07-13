package com.edwin.service;

import com.edwin.dao.UserDao;
import com.edwin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        User userByName = userDao.findByUserId(user.getId());
        if (userByName == null) {
            if (StringUtils.isEmpty(user.getUsername())) {
                throw new RuntimeException("Username is empty");
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                throw new RuntimeException("Email is empty");
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                throw new RuntimeException("Password is empty");
            }
            user.setStatus(1);
            user.setCreated_at(new Date());
            user.setUser_balance(new BigDecimal("0.0"));
            user.setUpdate_at(new Date());
            userDao.save(user);
        } else {
            throw new RuntimeException("User exists");
        }
    }

    @Override
    public User login(User user) {
        User userByName = userDao.findByUserName(user.getUsername());
        if (!ObjectUtils.isEmpty(userByName)) {
            if (userByName.getPassword().equals(user.getPassword())) {
                return userByName;
            } else {
                throw new RuntimeException("Password is not correct");
            }
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @Override
    public void update(User user) {
        User userByUserId = userDao.findByUserId(user.getId());
        if (userByUserId == null) {
            throw new RuntimeException("User does not exist");
        } else {
            userDao.update(user);
        }
    }
}
