package com.dmytro.pidluzhnyi.StudentTestingApp.dao;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDetailsRepository extends JpaRepository<TestDetails, Integer> {
}
