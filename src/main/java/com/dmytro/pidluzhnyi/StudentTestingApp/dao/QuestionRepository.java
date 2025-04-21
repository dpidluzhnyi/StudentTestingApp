package com.dmytro.pidluzhnyi.StudentTestingApp.dao;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//@RepositoryRestResource(path = "questionsss")
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
