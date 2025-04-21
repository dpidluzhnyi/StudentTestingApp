package com.dmytro.pidluzhnyi.StudentTestingApp.entity;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.util.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private Role role;

    public Authority() {

    }

    public Authority(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
