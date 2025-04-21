package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Difficulty;
import jakarta.persistence.*;

@Entity
@Table(name = "details")
public class TestDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_details_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject")
    private String subject;

    @Column(name = "difficulty")
    private Difficulty difficulty;

    public TestDetails() {
    }

    public TestDetails(String name, String subject, Difficulty difficulty) {
        this.name = name;
        this.subject = subject;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
