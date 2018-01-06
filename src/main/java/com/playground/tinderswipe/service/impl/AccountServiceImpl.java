package com.playground.tinderswipe.service.impl;

import com.playground.tinderswipe.model.Account;
import com.playground.tinderswipe.repository.AccountRepository;
import com.playground.tinderswipe.repository.entity.AccountEntity;
import com.playground.tinderswipe.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LogManager.getLogger();

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(@NotNull Account account) {
        AccountEntity entity = accountRepository.save(Account.toAccountEntity(account));
        logger.debug("account created:{}", entity);
        return Account.fromAccountEntity(entity);
    }

    @Override
    public List<Account> getAccounts(Collection<String> accountIds) {
        Iterable<AccountEntity> entities = accountRepository.findAll(accountIds);

        return StreamSupport.stream(entities.spliterator(), false)
                .map(Account::fromAccountEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllAccounts() {
        Iterable<AccountEntity> entities = accountRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
                .map(Account::fromAccountEntity)
                .collect(Collectors.toList());
    }
}
