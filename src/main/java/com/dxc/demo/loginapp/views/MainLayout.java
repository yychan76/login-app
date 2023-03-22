package com.dxc.demo.loginapp.views;

import java.util.Optional;

import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.dxc.demo.loginapp.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainLayout extends AppLayout {

    private final transient SecurityService securityService;
    private transient Optional<UserAccount> userAccountOpt;

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        userAccountOpt = securityService.fetchAuthenticatedUser();
        createHeader();
        createDrawer();
    }

    private void createHeader() {

        H1 logo = new H1(getUserDetailsLabel());
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        Button logout = new Button("Log out", e -> securityService.logout());
        logout.addThemeVariants(ButtonVariant.LUMO_SMALL);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.expand(logo);
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);

    }

    private String getUserDetailsLabel() {
        String userDetails = "";
        if (userAccountOpt.isPresent()) {
            UserAccount userAccount = userAccountOpt.get();
            userDetails = String.format("%s %s [%s - %s]",
                    userAccount.getFirstName(), userAccount.getLastName(),
                    userAccount.getUsername(),
                    userAccount.getRole().getName());
        }
        return userDetails;
    }

    private void createDrawer() {
        RouterLink userLink = new RouterLink("User App", UserAppView.class);
        userLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink managerLink = new RouterLink("Manager App", ManagerAppView.class);
        managerLink.setHighlightCondition(HighlightConditions.sameLocation());
        managerLink.setVisible(false);
        if (userAccountOpt.isPresent()) {
            managerLink.setVisible(userAccountOpt.get().isManager());
        }

        addToDrawer(new VerticalLayout(
                userLink,
                managerLink));
    }
}
