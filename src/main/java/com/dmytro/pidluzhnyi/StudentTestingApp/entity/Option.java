package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option implements Comparable<Option> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "answer")
    private boolean answer;

    public Option() {
    }

    public Option(String text, boolean answer) {
        this.text = text;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public int compareTo(Option o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
