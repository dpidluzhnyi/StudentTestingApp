package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.UserRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import com.dmytro.pidluzhnyi.StudentTestingApp.security.AuthenticationFacade;
import com.dmytro.pidluzhnyi.StudentTestingApp.security.CustomUserDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User getAuthenticatedUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) authenticationFacade.getAuthentication().getPrincipal();
        return customUserDetails.getUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
