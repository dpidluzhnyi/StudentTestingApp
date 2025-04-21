package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Difficulty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int id;

    @Column(name = "time_to_complete")
    private int timeToComplete;

    @Column(name = "view_number")
    private int viewNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "test_id")
    private Set<Question> questions = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "test_details_id", referencedColumnName = "test_details_id")
    private TestDetails testDetails;

    public Test() {
    }

    public Test(int timeToComplete, int viewNumber) {
        this.timeToComplete = timeToComplete;
        this.viewNumber = viewNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void addQuestions(Set<Question> questions) {
        this.questions.addAll(questions);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public TestDetails getTestDetails() {
        return testDetails;
    }

    public void setTestDetails(TestDetails testDetails) {
        this.testDetails = testDetails;
    }
}
