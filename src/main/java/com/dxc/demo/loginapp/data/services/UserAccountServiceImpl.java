package com.dxc.demo.loginapp.data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dxc.demo.loginapp.data.entities.UserAccount;
import com.dxc.demo.loginapp.data.repositories.UserAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Optional<UserAccount> saveUserAccount(UserAccount userAccount) {
        if (userAccount == null) {
            log.warn("User Account must be provided.");
            return Optional.empty();
        }
        return Optional.of(userAccountRepository.save(userAccount));
    }

    @Override
    public Optional<UserAccount> findUserAccountByUsername(String userName) {
        return userAccountRepository.findByUsername(userName);
    }

    @Override
    public List<UserAccount> findAllUserAccounts() {
        return userAccountRepository.findAll();
    }

}
