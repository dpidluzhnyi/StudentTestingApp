package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.ResultRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Result;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ResultService {
    List<Result> getResults();
    Result getResult(int resultId);
    List<Result> getResultsByUserId(int userId);
    Result saveResult(LocalDateTime completionTime, double score, TestDetails testDetails);
    Result saveResult(Result result);
    void deleteResult(Result result);
    double calculateResult(Map<String, String> items);
}
