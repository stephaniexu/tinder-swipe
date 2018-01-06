package com.playground.tinderswipe.service;

import com.playground.tinderswipe.model.Account;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public interface AccountService {
    Account createAccount(@NotNull Account account);

    List<Account> getAccounts(Collection<String> accountIds);

    List<Account> getAllAccounts();
}
