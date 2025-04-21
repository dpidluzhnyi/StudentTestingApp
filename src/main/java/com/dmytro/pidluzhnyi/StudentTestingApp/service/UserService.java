package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getUsers();
    User getUser(int userId);
    User getAuthenticatedUser();
    User getUserByUsername(String username);
    User saveUser(User user);
    void deleteUser(User user);
}
