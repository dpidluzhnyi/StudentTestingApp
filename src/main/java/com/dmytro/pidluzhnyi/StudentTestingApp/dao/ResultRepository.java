package com.dmytro.pidluzhnyi.StudentTestingApp.dao;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findByUserId(int id);
}
