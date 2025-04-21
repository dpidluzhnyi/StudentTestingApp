package com.dmytro.pidluzhnyi.StudentTestingApp.service;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Option;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface OptionService {
    List<Option> getOptions();
    Page<Option> getOptions(Pageable pageable);
    Option getOption(int optionId);
    Option saveOption(Option option);
    List<Option> saveOptions(Set<Option> options);
    void deleteOption(Option option);
    boolean isOptionCorrect(int id);
}
