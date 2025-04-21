package com.dmytro.pidluzhnyi.StudentTestingApp.controller;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.Authority;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Role;
import com.dmytro.pidluzhnyi.StudentTestingApp.service.UserService;
import com.dmytro.pidluzhnyi.StudentTestingApp.testdata.InitData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String redirectToMain(){
        return "redirect:/app/student/tests";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        user.addAuthorities(List.of(new Authority(Role.ROLE_STUDENT)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(bindingResult.hasErrors()){
            return "register";
        }

        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return "register";
        }
        return "redirect:/login?register=true";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        model.addAttribute("logout", true);
        return "login";
    }

    @RequestMapping("/initData")
    public String initData(){
        InitData.fillData();
        return "test-dashboard";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
