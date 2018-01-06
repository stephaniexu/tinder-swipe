package com.playground.tinderswipe.controller;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {
    private static final Logger logger = LogManager.getLogger();

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping
    public Account createAccount(@RequestBody Account account) {
        logger.debug("create account:{}", account);
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
