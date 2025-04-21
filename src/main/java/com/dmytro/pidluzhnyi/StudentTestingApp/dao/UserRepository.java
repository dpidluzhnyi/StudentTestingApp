package com.dmytro.pidluzhnyi.StudentTestingApp.dao;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
