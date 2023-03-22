package com.dxc.demo.loginapp.views;

import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.dxc.demo.loginapp.data.services.RoleService;
import com.dxc.demo.loginapp.data.services.UserAccountService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "/management", layout = MainLayout.class)
@PageTitle("Management Page - Restricted")
public class ManagerAppView extends VerticalLayout {

  private final transient UserAccountService userAccountService;
  private final transient RoleService roleService;
  Grid<UserAccount> grid = new Grid<>(UserAccount.class);
  UserAccountForm form;
  Button backButton = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT));

  public ManagerAppView(UserAccountService userAccountService, RoleService roleService) {
    this.userAccountService = userAccountService;
    this.roleService = roleService;
    configureGrid();
    configureForm();
    configureBackButton();
    add(
        new H1("Welcome to Management Page"),
        getContent(),
        backButton);
    updateList();
    closeEditor();
  }

  private Component getContent() {
    HorizontalLayout content = new HorizontalLayout(grid, form);
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, form);
    content.setSizeFull();
    return content;
  }

  private void configureGrid() {
    grid.setColumns("firstName", "lastName", "username");
    grid.addColumn(userAccount -> userAccount.getRole().getName())
        .setHeader("Role").setSortable(true);
    grid.getColumns().forEach(column -> column.setAutoWidth(true));

    grid.asSingleSelect().addValueChangeListener(event -> editUserAccount(event.getValue()));
  }

  private void configureForm() {
    form = new UserAccountForm(roleService.findAllRoles());
    form.setWidth("7em");
    form.addListener(UserAccountForm.SaveEvent.class, this::saveUserAccount);
    form.addListener(UserAccountForm.CancelEvent.class, event -> closeEditor());
  }

  private void editUserAccount(UserAccount userAccount) {
    if (userAccount == null) {
      closeEditor();
    } else {
      form.setUserAccount(userAccount);
      form.setVisible(true);

    }
  }

  private void saveUserAccount(UserAccountForm.SaveEvent event) {
    userAccountService.saveUserAccount(event.getUserAccount());
    updateList();
    closeEditor();
  }

  private void closeEditor() {
    form.setUserAccount(null);
    form.setVisible(false);
  }

  private void updateList() {
    grid.setItems(userAccountService.findAllUserAccounts());
  }

  private void configureBackButton() {
    backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    backButton.addClickListener(click -> {
      backButton.getUI().ifPresent(ui -> ui.navigate("/"));
    });
  }

}
