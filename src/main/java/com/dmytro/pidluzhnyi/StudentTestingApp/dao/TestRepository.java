package com.dmytro.pidluzhnyi.StudentTestingApp.dao;


import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
