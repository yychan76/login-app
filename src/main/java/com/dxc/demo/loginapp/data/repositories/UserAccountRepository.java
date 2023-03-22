package com.dxc.demo.loginapp.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.demo.loginapp.data.entities.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);

}
