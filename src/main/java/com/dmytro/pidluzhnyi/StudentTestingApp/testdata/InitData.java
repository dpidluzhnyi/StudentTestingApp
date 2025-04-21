package com.dmytro.pidluzhnyi.StudentTestingApp.testdata;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.*;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Difficulty;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Role;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InitData {
    private static PasswordEncoder passwordEncoder;
    private static UserService userService;
    private static QuestionService questionService;
    private static TestService testService;
    private static TestDetailsService testDetailsService;

    @Autowired
    public InitData(UserService userService, QuestionService questionService, TestService testService,
                    TestDetailsService testDetailsService, PasswordEncoder passwordEncoder){
        InitData.userService = userService;
        InitData.questionService = questionService;
        InitData.testService = testService;
        InitData.testDetailsService = testDetailsService;
        InitData.passwordEncoder = passwordEncoder;
    }
    public static void fillData() {
        addStudents();
        addTests();
    }

    public static void addStudents() {
        User student1 =
                new User("John", "Dow", "jdow@email.com", "john", passwordEncoder.encode("test1"));
        student1.addAuthorities(List.of(new Authority(Role.ROLE_STUDENT)));
        User student2 =
                new User("Marta", "Smart", "msmart@email.com", "marta", passwordEncoder.encode("test2"));
        student2.addAuthorities(List.of(new Authority(Role.ROLE_ADMIN)));
        userService.saveUser(student1);
        userService.saveUser(student2);
    }

    public static void addTests() {
        Test t1 = new Test(1,0);
        Set<Question> q1 = Set.of(
                createQuestion("What is 2+2?",
                        Map.of(
                        "1", false,
                        "2", false,
                        "5", false,
                        "4", true
                        )
                ),
                createQuestion("What is 10+5?",
                        Map.of(
                        "15", true,
                        "23", false,
                        "3",  false,
                        "14", false
                        )
                ),
                createQuestion("Meaning of life?",
                        Map.of(
                        "None", false,
                        "Do cool stuff", true,
                        "43",  false
                        )
                )
        );
        t1.addQuestions(q1);
        TestDetails ts1 = new TestDetails("Math Test #1","Math", Difficulty.HARD);
        t1.setTestDetails(ts1);
        testDetailsService.saveTestDetails(ts1);
        testService.saveTest(t1);
        Test t2 = new Test(1,0);
        Set<Question> q2 = Set.of(
                createQuestion("Bla bla bla?",
                        Map.of(
                                "yes", true,
                                "no", false,
                                "maybe",  false,
                                "0", true
                        )
                ),
                createQuestion("What is 11+11?",
                        Map.of(
                                "22", true,
                                "33", false,
                                "2",  false,
                                "1", false
                        )
                )
        );
        t2.addQuestions(q2);
        TestDetails ts2 = new TestDetails("Logic Test #2","Logic", Difficulty.EASY);
        t2.setTestDetails(ts2);
        testDetailsService.saveTestDetails(ts2);
        testService.saveTest(t2);
    }

    public static Question createQuestion(String text, Map<String, Boolean> args) {
        Question q = new Question(text);
        Set<Option>options = new HashSet<>();
        args.forEach((k, v) -> options.add(new Option(k, v)));
        q.addOptions(options);
        questionService.saveQuestion(q);
        return q;
    }
}
