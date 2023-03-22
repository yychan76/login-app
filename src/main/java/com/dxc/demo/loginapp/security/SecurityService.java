package com.dxc.demo.loginapp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.dxc.demo.loginapp.data.services.UserAccountService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityService {

    private static final String LOGOUT_SUCCESS_URL = "/";

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    UserAccountService userAccountService;

    public Optional<UserAccount> fetchAuthenticatedUser() {
        Optional<Authentication> authOpt = authenticationFacade.getAuthentication();
        if (authOpt.isPresent()) {
            Object principal = authOpt.get().getPrincipal();
            log.info("principal: {}", principal);
            String username = ((User) principal).getUsername();
            return userAccountService.findUserAccountByUsername(username);
        }
        return Optional.empty();
    }

    public void logout() {
        UI.getCurrent().getPage().setLocation(LOGOUT_SUCCESS_URL);
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(), null,
                null);
    }

}
