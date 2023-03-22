package com.dxc.demo.loginapp.views;

import java.util.Optional;

import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.dxc.demo.loginapp.security.SecurityService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "", layout = MainLayout.class)
@PageTitle("Welcome Page")
public class WelcomeView extends VerticalLayout {

  private transient Optional<UserAccount> userAccountOpt;

  public WelcomeView(SecurityService securityService) {
    userAccountOpt = securityService.fetchAuthenticatedUser();

    RouterLink userLink = new RouterLink("Go to user app", UserAppView.class);
    RouterLink managerLink = new RouterLink("Go to manager app", ManagerAppView.class);
    managerLink.setVisible(false);
    if (userAccountOpt.isPresent()) {
      managerLink.setVisible(userAccountOpt.get().isManager());
    }

    add(
        new H1("Welcome to Test App"),
        userLink,
        managerLink);
  }
}
