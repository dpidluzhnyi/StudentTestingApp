package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "Firstname is required")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Lastname is required")
    private String lastName;

    @Column(name = "email")
    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "role_id")
    private List<Authority> authorities = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Result> results = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void addAuthorities(List<Authority> authorities){
        this.authorities.addAll(authorities);
    }

    public void removeAuthorities(List<Authority> authorities){
        this.authorities.removeAll(authorities);
    }

    public List<Result> getResults(){
        return results;
    }

    public void addResult(Result result){
        this.results.add(0, result);
    }

    public void addResults(List<Result> results){
        this.results.addAll(results);
    }

    public void updateResult(Result result){
        this.results.remove(0);
        addResult(result);
    }

    public void removeResults(List<Result> results){
        this.results.removeAll(results);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User " + firstName + " " + lastName +", email='" + email + '\'';
    }
}
