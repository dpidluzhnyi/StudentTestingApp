package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    List<Question> getQuestions();
    Page<Question> getQuestions(Pageable pageable);
    Question getQuestion(int questionId);
    Question saveQuestion(Question question);
    Question updateQuestion(Map<String,String> allParams);
    Question addOptionToQuestion(int questionId);
    void deleteOption(int questionId, int optionId);
    void deleteQuestion(Question question);
}
