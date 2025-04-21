package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.QuestionRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Option;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.OptionService;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionService optionService;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, OptionService optionService){
        this.questionRepository = questionRepository;
        this.optionService = optionService;
    }

    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Page<Question> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question getQuestion(int questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Map<String,String> allParams) {
        Question q = getQuestion(Integer.parseInt(allParams.get("questionId")));
        updateOptions(allParams, "option");
        updateOptions(allParams, "answer");
        return saveQuestion(q);
    }

    private void updateOptions(Map<String,String> allParams, String type) {
        allParams.forEach((k,v) -> {
            if(k.contains(type)){
                int optionId = Integer.parseInt(k.substring(k.lastIndexOf("_")+1));
                Option option = optionService.getOption(optionId);
                if(type.equals("option")){
                    option.setText(v);
                    option.setAnswer(false);
                } else {
                    option.setAnswer(true);
                }
            }
        });
    }

    @Override
    public Question addOptionToQuestion(int questionId) {
        Question question = getQuestion(questionId);
        question.addOption(new Option("Enter text", false));
        return saveQuestion(question);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public void deleteOption(int questionId, int optionId) {
        Question question = getQuestion(questionId);
        Optional<Option> option = question.getOption(optionId);
        option.ifPresent(optionService::deleteOption);
    }
}
