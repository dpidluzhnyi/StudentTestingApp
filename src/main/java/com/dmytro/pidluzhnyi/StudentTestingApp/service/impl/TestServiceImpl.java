package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.TestRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Option;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Difficulty;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.OptionService;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.QuestionService;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.TestDetailsService;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final TestDetailsService testDetailsService;

    @Autowired
    private TestServiceImpl(TestRepository testRepository, QuestionService questionService, OptionService optionService,
        TestDetailsService testDetailsService) {
        this.testRepository = testRepository;
        this.questionService = questionService;
        this.optionService = optionService;
        this.testDetailsService = testDetailsService;
    }

    @Override
    public Test updateTest(Map<String,String> allParams) {
        Test updatedTest = getTest(Integer.parseInt(allParams.get("testId")));

        String testName = allParams.get("testName");
        String subject = allParams.get("subject");
        String difficulty = allParams.get("difficulty");
        updatedTest.setTimeToComplete(Integer.parseInt(allParams.get("timeToComplete")));
        TestDetails testDetails = new TestDetails(testName, subject, Difficulty.valueOf(difficulty));
        updatedTest.setTestDetails(testDetails);

        return updatedTest;
    }

    @Override
    public List<Test> getTests() {
        return testRepository.findAll();
    }

    @Override
    public Page<Test> getTests(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    @Override
    public Test getTest(int testId) {
        return testRepository.findById(testId).orElse(null);
    }

    @Override
    public Test saveTest(Test test) {
        testDetailsService.saveTestDetails(test.getTestDetails());
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }
}
