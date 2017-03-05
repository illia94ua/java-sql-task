package com.acceptic.task.service;

import java.util.List;

import com.acceptic.task.model.User;
import com.acceptic.task.repository.UserRepository;
import com.acceptic.task.repository.sql.UserRepositorySql;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositorySql();
    }

    public List<User> createUser(User user) {
        return userRepository.createUser(user);
    }

    public List<User> updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public List<User> getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

}
