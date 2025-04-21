package com.dmytro.pidluzhnyi.StudentTestingApp.dao;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Integer> {
}

