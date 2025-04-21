package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private int id;

    @Column(name = "completion_time")
    private LocalDateTime completionTime;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "score")
    private double score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_details_id", referencedColumnName = "test_details_id")
    private TestDetails testDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Result() {
    }

    public Result(LocalDateTime completionTime, double score, TestDetails testDetails) {
        this.completionTime = completionTime;
        this.score = score;
        this.testDetails = testDetails;
        this.date = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompletionTime() {
        long totalSecs = ChronoUnit.SECONDS.between(date, completionTime);

        long hours = totalSecs / 3600;
        long minutes = (totalSecs % 3600) / 60;
        long seconds = totalSecs % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public String getScore() {
        return String.format("%.2f", score);
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return completionTime.format(formatter);
    }

    public LocalDateTime getLocalDateTime(){
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public TestDetails getTestDetails() {
        return testDetails;
    }

    public void setTestDetails(TestDetails testDetails) {
        this.testDetails = testDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
