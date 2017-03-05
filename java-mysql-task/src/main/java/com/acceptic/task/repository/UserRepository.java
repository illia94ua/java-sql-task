package com.acceptic.task.repository;

import java.util.List;

import com.acceptic.task.model.User;

public interface UserRepository {

    List<User> createUser(User user);

    List<User> updateUser(User user);

    List<User> findAllUsers();

    List<User> getUserById(int userId);

}
