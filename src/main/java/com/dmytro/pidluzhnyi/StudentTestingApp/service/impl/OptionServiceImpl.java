package com.dmytro.pidluzhnyi.StudentTestingApp.service.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.dao.OptionRepository;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Option;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Page<Option> getOptions(Pageable pageable) {
        return optionRepository.findAll(pageable);
    }

    @Override
    public Option getOption(int optionId) {
        return optionRepository.findById(optionId).orElse(null);
    }

    @Override
    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    @Override
    public List<Option> saveOptions(Set<Option> options) {
        return optionRepository.saveAll(options);
    }

    @Override
    public void deleteOption(Option option) {
        optionRepository.delete(option);
    }

    @Override
    public boolean isOptionCorrect(int id) {
        return getOption(id).isAnswer();
    }
}
