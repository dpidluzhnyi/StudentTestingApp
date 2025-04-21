package com.dmytro.pidluzhnyi.StudentTestingApp.controller;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Result;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import com.dmytro.pidluzhnyi.StudentTestingApp.security.AuthenticationFacade;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class StudentController {
    private final UserService userService;
    private final TestService testService;
    private final TestDetailsService testDetailsService;
    private final ResultService resultService;

    public StudentController(UserService userService, TestService testService, TestDetailsService testDetailsService,
                             ResultService resultService) {
        this.userService = userService;
        this.testService = testService;
        this.testDetailsService = testDetailsService;
        this.resultService = resultService;
    }

    @GetMapping("/student/tests")
    public String getTests(Model model, Pageable pageable) {
        model.addAttribute("tests", testService.getTests(pageable));
        return "test-dashboard";
    }

    @PostMapping("/student/tests/calcResult")
    public String calcResult(@RequestParam Map<String, String> allParams, Model model) {
        double score = resultService.calculateResult(allParams);
        int resultId = Integer.parseInt(allParams.get("resultId"));
        User user = userService.getAuthenticatedUser();
        Result result = user.getResults().stream().filter(s -> s.getId() == resultId).findAny()
                .orElse(resultService.getResult(resultId));
        result.setScore(score);
        result.setCompletionTime(LocalDateTime.now());
        userService.saveUser(user);
        model.addAttribute("results", user.getResults());

        return "redirect:/app/student/" + userService.getAuthenticatedUser().getId() + "/results";
    }

    @GetMapping("/student/{userId}/results")
    public String getResults(@PathVariable int userId, Model model) {
        model.addAttribute("results", resultService.getResultsByUserId(userId));
        return "student-results";
    }

    @GetMapping("/student/{userId}/account")
    public String getAccount(@PathVariable int userId, Model model) {
        User user = userService.getAuthenticatedUser();
        if(userId == user.getId()){
            model.addAttribute("user", userService.getAuthenticatedUser());
            model.addAttribute("results", resultService.getResultsByUserId(userId));
            return "account";
        }
        return "error";
    }

    @PutMapping("/student/{userId}/account")
    public String updateAccount(@PathVariable int userId, Model model) {
        return "account";
    }

    @PostMapping("/student/tests/{id}/startTest")
    public String startTest(@PathVariable int id, Model model) {
        Test test = testService.getTest(id);
        if (test == null) {
            throw new RuntimeException("Test doesn't exist - " + id);
        }
        test.setViewNumber(test.getViewNumber() + 1);
        testService.saveTest(test);

        TestDetails testDetails = testDetailsService.getTestDetails(id);
        Result result = new Result(LocalDateTime.now(), 0, testDetails);
        result.setDate(LocalDateTime.now());
        result.setUser(userService.getAuthenticatedUser());

        Result updatedResult = resultService.saveResult(result);

        return "redirect:/app/student/tests/" + id + "/startTest/" + updatedResult.getId();
    }

    @GetMapping("/student/tests/{testId}/startTest/{resultId}")
    public String startTest(@PathVariable int testId, @PathVariable int resultId, Model model) {
        Test test = testService.getTest(testId);
        Result result = resultService.getResult(resultId);
        if (test == null || result == null) {
            throw new RuntimeException("Test doesn't exist");
        }
        model.addAttribute("test", test);
        model.addAttribute("timer", result.getLocalDateTime());
        model.addAttribute("result", result);
        return "test-process";
    }
}
