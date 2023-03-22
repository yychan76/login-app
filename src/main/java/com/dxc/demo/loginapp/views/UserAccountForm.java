package com.dxc.demo.loginapp.views;

import java.util.List;

import com.dxc.demo.loginapp.data.entities.Role;
import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAccountForm extends FormLayout {
    private UserAccount userAccount;

    TextField firstName = new TextField("First Name");
    TextField lastName = new TextField("Last Name");
    ComboBox<Role> role = new ComboBox<>("Role");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");
    Binder<UserAccount> binder = new BeanValidationBinder<>(UserAccount.class);

    public UserAccountForm(List<Role> roles) {
        binder.bindInstanceFields(this);
        role.setItems(roles);
        role.setItemLabelGenerator(Role::getName);

        add(firstName,
                lastName,
                role,
                createButtonsLayout());
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        binder.readBean(userAccount);
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        cancel.addClickListener(click -> fireEvent(new CancelEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(userAccount);
            fireEvent(new SaveEvent(this, userAccount));
        } catch (ValidationException ve) {
            log.error(ve.toString(), ve);
        }
    }

    public abstract static class UserAccountFormEvent extends ComponentEvent<UserAccountForm> {
        private UserAccount userAccount;

        protected UserAccountFormEvent(UserAccountForm source, UserAccount userAccount) {
            super(source, false);
            this.userAccount = userAccount;
        }

        public UserAccount getUserAccount() {
            return userAccount;
        }
    }

    public static class SaveEvent extends UserAccountFormEvent {
        SaveEvent(UserAccountForm source, UserAccount userAccount) {
            super(source, userAccount);
        }
    }

    public static class CancelEvent extends UserAccountFormEvent {
        CancelEvent(UserAccountForm source) {
            super(source, null);
        }
    }

    @Override
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
            ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
