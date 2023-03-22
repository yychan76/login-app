package com.dxc.demo.loginapp.data.services;

import java.util.List;
import java.util.Optional;

import com.dxc.demo.loginapp.data.entities.UserAccount;

public interface UserAccountService {

    Optional<UserAccount> saveUserAccount(UserAccount userAccount);

    Optional<UserAccount> findUserAccountByUsername(String username);

    List<UserAccount> findAllUserAccounts();

}
