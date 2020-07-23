package com.edwin.service;

import com.edwin.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);

    void update(User user);

    void resetPassword(String newPassword, User currentUser);
}
