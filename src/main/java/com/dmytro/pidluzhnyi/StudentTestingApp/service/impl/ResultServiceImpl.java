package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.QuestionRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.dao.ResultRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Result;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.OptionService;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final OptionService optionService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, OptionService optionService){
        this.resultRepository = resultRepository;
        this.optionService = optionService;
    }

    @Override
    public List<Result> getResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result getResult(int resultId) {
        return resultRepository.findById(resultId).orElse(null);
    }

    @Override
    public List<Result> getResultsByUserId(int userId) {
        return resultRepository.findByUserId(userId);
    }

    @Override
    public Result saveResult(LocalDateTime completionTime, double score, TestDetails testDetails) {
        return resultRepository.save(new Result(completionTime, score, testDetails));
    }

    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(Result result) {
        resultRepository.delete(result);
    }

    @Override
    public double calculateResult(Map<String, String> items) {
        int count = items.entrySet().stream()
                .filter(entry ->
                        !isNumeric(entry.getValue()) &&
                        optionService.isOptionCorrect(Integer.parseInt(entry.getKey())) != Boolean.getBoolean(entry.getValue())
                )
                .toList().size();

        if(count == 0) {
            return 0;
        }
        return 100 * (double) count / (items.size() - 1);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
