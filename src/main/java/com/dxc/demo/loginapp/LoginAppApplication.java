package com.dxc.demo.loginapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;

@SpringBootApplication
@Theme(value="my-theme")
public class LoginAppApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(LoginAppApplication.class, args);
	}

}
