package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TestService {
    Test updateTest(Map<String,String> allParams);
    List<Test> getTests();
    Page<Test> getTests(Pageable pageable);
    Test getTest(int testId);
    Test saveTest(Test test);
    void deleteTest(Test test);
}
