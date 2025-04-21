package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "question")
public class Question implements Comparable<Question> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(name = "text")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private Set<Option> options = new LinkedHashSet<>();

    public Question() {
    }

    public Question(String text) {
        this.text = text;
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

    public Set<Option> getOptions() {
        return options;
    }

    public Optional<Option> getOption(int optionId) {
        Optional<Option> optionalOption = Optional.empty();
        for (Option option : options) {
            if (option.getId() == optionId)
                optionalOption = Optional.of(option);
        }
        return optionalOption;
    }

    public Option addOption(Option option) {
        this.options.add(option);
        return option;
    }

    public void addOptions(Set<Option> options) {
        this.options.addAll(options);
    }

    public void removeOption(Option option) {
        this.options.remove(option);
    }

    @Override
    public int compareTo(Question o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
