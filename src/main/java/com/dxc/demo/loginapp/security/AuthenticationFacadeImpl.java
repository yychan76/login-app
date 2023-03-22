package com.dxc.demo.loginapp.security;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Optional<Authentication> getAuthentication() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       if (!(authentication instanceof AnonymousAuthenticationToken)) {
           return Optional.of(authentication);
       }
       return Optional.empty();
    }
}
