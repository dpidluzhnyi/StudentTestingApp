package com.dmytro.pidluzhnyi.StudentTestingApp.controller;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Question;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Test;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.TestDetails;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/app")
public class AdminController {
    private final UserService userService;
    private final TestService testService;
    private final TestDetailsService testDetailsService;
    private final QuestionService questionService;

    public AdminController(UserService userService, TestService testService, TestDetailsService testDetailsService,
            QuestionService questionService){
        this.userService = userService;
        this.testService = testService;
        this.testDetailsService = testDetailsService;
        this.questionService = questionService;
    }

    @GetMapping("/admin/tests")
    public String getEditableTests(Model model) {
        model.addAttribute("tests",  testService.getTests());
        return "editable-test-dashboard";
    }

    @PostMapping("/admin/tests/createTest")
    public String createTest(Model model) {
        Test test = new Test(0, 0);
        TestDetails testDetails = new TestDetails();
        test.setTestDetails(testDetails);
        testDetailsService.saveTestDetails(testDetails);
        testService.saveTest(test);
        model.addAttribute("test", test);
        return "create-test";
    }

    @PostMapping("/admin/tests/{id}/editTest")
    public String saveTest(@PathVariable int id, @RequestParam Map<String,String> allParams, Model model) {
        Test updatedTest = testService.updateTest(allParams);
        testService.saveTest(updatedTest);
        return "redirect:/app/admin/tests";
    }

    @PostMapping(value = "/admin/tests/{id}/updateTest", params = "update")
    public String updateTest(@PathVariable int id, @RequestParam Map<String,String> allParams) {
        Test updatedTest = testService.updateTest(allParams);
        testService.saveTest(updatedTest);
        return "redirect:/app/admin/tests/"+allParams.get("testId")+"/editTest";
    }

    @GetMapping("/admin/tests/{id}/editTest")
    public String editTest(@PathVariable int id, Model model) {
        Test t = testService.getTest(id);
        if(t == null){
            throw new RuntimeException("Test doesn't exist - "+ id);
        }
        model.addAttribute("test", t);
        return "edit-test";
    }

    @DeleteMapping("/admin/tests/{id}")
    public String deleteTest(@PathVariable int id) {
        Test t = testService.getTest(id);
        if(t == null){
            throw new RuntimeException("Test doesn't exist - "+ id);
        }
        testService.deleteTest(t);
        return "redirect:/app/admin/tests";
    }

    @PostMapping(value = "/admin/questions/{id}/updateQuestion", params = "update")
    public String updateQuestion(@PathVariable int id, @RequestParam Map<String,String> allParams) {
        questionService.updateQuestion(allParams);
        return "redirect:/app/admin/tests/"+allParams.get("testId")+"/editTest";
    }

    @PostMapping(value = "/admin/questions/{id}/updateQuestion", params = "add")
    public String addOption(@PathVariable int id, @RequestParam Map<String,String> allParams) {
        questionService.addOptionToQuestion(Integer.parseInt(allParams.get("questionId")));
        return "redirect:/app/admin/tests/"+allParams.get("testId")+"/editTest";
    }

    @PostMapping(value = "/admin/questions/{id}/updateQuestion", params = "remove")
    public String removeOption(@PathVariable int id, @RequestParam Map<String,String> allParams) {
        questionService.deleteOption(Integer.parseInt(allParams.get("questionId")),
                Integer.parseInt(allParams.get("optionId")));
        return "redirect:/app/admin/tests/"+allParams.get("testId")+"/editTest";
    }

    @PostMapping(value = "/admin/questions", params = "add")
    public String addQuestion(@RequestParam Map<String,String> allParams) {
        Test test = testService.getTest(Integer.parseInt(allParams.get("testId")));
        test.addQuestion(new Question("Enter question"));
        testService.saveTest(test);
        return "redirect:/app/admin/tests/"+allParams.get("testId")+"/editTest";
    }
}
