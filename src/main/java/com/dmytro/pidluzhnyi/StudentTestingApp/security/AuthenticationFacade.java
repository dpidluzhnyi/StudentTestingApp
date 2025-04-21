package com.dmytro.pidluzhnyi.StudentTestingApp.security;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}