package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;

import java.util.List;

public interface TestDetailsService {
    List<TestDetails> getTestDetails();

    TestDetails getTestDetails(int tstDetailsId);

    TestDetails saveTestDetails(TestDetails testDetails);

    void deleteTestDetails(TestDetails testDetails);
}
