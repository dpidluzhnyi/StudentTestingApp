package com.dmytro.pidluzhnyi.StudentTestingApp.security.impl;

import com.dmytro.pidluzhnyi.StudentTestingApp.entity.User;
import com.dmytro.pidluzhnyi.StudentTestingApp.security.AuthenticationFacade;
import com.dmytro.pidluzhnyi.StudentTestingApp.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}