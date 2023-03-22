package com.dxc.demo.loginapp.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "/app", layout = MainLayout.class)
@PageTitle("User App")
public class UserAppView extends VerticalLayout {

  VerticalLayout todosList = new VerticalLayout();
  TextField taskField = new TextField();
  Button addButton = new Button("Add");
  Button backButton = new Button("Back", new Icon(VaadinIcon.ARROW_LEFT));

  public UserAppView() {
    configureTasks();
    configureBackButton();

    add(
        new H1("Welcome To Your Todos"),
        todosList,
        new HorizontalLayout(
            taskField,
            addButton),
        backButton);
  }

  private void configureTasks() {
    addButton.addClickListener(click -> {
      Checkbox checkbox = new Checkbox(taskField.getValue());
      checkbox.addValueChangeListener(check -> {
        if (check.getValue()) {
          checkbox.addClassName("done");
        } else {
          checkbox.removeClassName("done");
        }
      });
      todosList.add(checkbox);
      taskField.clear();
    });
    addButton.addClickShortcut(Key.ENTER);
  }

  private void configureBackButton() {
    backButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    backButton.addClickListener(click -> {
      backButton.getUI().ifPresent(ui -> ui.navigate("/"));
    });
  }
}
