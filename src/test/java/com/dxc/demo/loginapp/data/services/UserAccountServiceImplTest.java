package com.dxc.demo.loginapp.data.services;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.dxc.demo.loginapp.data.entities.Role;
import com.dxc.demo.loginapp.data.entities.UserAccount;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@ExtendWith(SoftAssertionsExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled("Disabled until troubleshoot com.vaadin.flow.di.Lookup race condition")
class UserAccountServiceImplTest {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    RoleService roleService;

    @InjectSoftAssertions
    SoftAssertions softly;

    @Test
    void testAddUserAccount() {
        String username = "larry.fisher";
        Role role = roleService.findAllRoles().get(0);
        log.info("Role: {}", role);
        Optional<UserAccount> saved = userAccountService.saveUserAccount(UserAccount.builder()
                .firstName("Larry")
                .lastName("Fisher")
                .username(username)
                .password("pw3")
                .role(role)
                .build());
        softly.assertThat(saved).isPresent();
        softly.assertThat(saved.get().getUsername()).isEqualTo(username);
    }

    @Test
    void testFindAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountService.findAllUserAccounts();
        log.info("UserAccounts: {}", userAccounts);
        softly.assertThat(userAccounts).isNotEmpty();
    }

    @Test
    void testFindUserAccountByUsername() {
        String username = "olivia.huang";
        Optional<UserAccount> found = userAccountService.findUserAccountByUsername(username);
        softly.assertThat(found).isPresent();
        softly.assertThat(found.get().getUsername()).isEqualTo(username);
    }
}
