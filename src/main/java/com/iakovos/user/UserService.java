package com.iakovos.user;

import java.util.Optional;
import java.util.UUID;

public class UserService {

    private final UserDao userDao = new UserDao();

    public User[] getAllUsers() {
        return userDao.findAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.findById(id);
    }
}
