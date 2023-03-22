package com.dxc.demo.loginapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dxc.demo.loginapp.views.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled from USER_ACCOUNT where username = ?")
                .authoritiesByUsernameQuery("select username, authority from USER_ACCOUNT u join ROLE r on r.id = u.role_id where username = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**", "/login", "/").permitAll()
                        .requestMatchers("/management/**").hasRole("MANAGER")
                        .requestMatchers("/app/**").authenticated());
        super.configure(httpSecurity);
        setLoginView(httpSecurity, LoginView.class);
    }
}
