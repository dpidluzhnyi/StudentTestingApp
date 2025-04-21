package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.TestDetailsRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.TestDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDetailsServiceImpl implements TestDetailsService {

    private final TestDetailsRepository testDetailsRepository;

    @Autowired
    public TestDetailsServiceImpl(TestDetailsRepository testDetailsRepository) {
        this.testDetailsRepository = testDetailsRepository;
    }

    @Override
    public List<TestDetails> getTestDetails() {
        return testDetailsRepository.findAll();
    }

    @Override
    public TestDetails getTestDetails(int testDetailsId) {
        return testDetailsRepository.findById(testDetailsId).orElse(null);
    }

    @Override
    public TestDetails saveTestDetails(TestDetails testDetails) {
        return testDetailsRepository.save(testDetails);
    }

    @Override
    public void deleteTestDetails(TestDetails testDetails) {
        testDetailsRepository.delete(testDetails);
    }
}
