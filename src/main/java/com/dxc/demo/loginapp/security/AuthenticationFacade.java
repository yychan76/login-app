package com.dxc.demo.loginapp.security;

import java.util.Optional;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Optional<Authentication> getAuthentication();
}
