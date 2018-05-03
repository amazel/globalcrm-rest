package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.AccountMapper;
import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountHistoryRepository accountHistoryRepository;
    private AccountMapper accountMapper = AccountMapper.INSTANCE;

    public AccountServiceImpl(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
    }

    @Override
    @Transactional
    public AccountDTO createAccount(AccountDTO accountDTO) {
        log.info("CREATING ACCOUNT");
        Account account = accountMapper.dtoToAccount(accountDTO);
        account.setAccountStatus(AccountStatus.NEW);
        account.setCreationDateTime(LocalDateTime.now());
        account.setExpirationDateTime(LocalDateTime.now().plusDays(30));
        account.setSubscriptionType(SubscriptionType.MICRO);
        account.getAccountHolder().setPassword(null);
        account.getAccountHolder().setId(null);
        account.setUsers(new HashSet<>());
        account.setCompanies(new HashSet<>());
        Account savedAcct = accountRepository.save(account);
        savedAcct.getAccountHolder().setAccount(savedAcct);
        Account retAcct = accountRepository.save(savedAcct);
        generateAccountHistoryRecord(retAcct, AccountEvent.CREATED);
        return accountMapper.accountToDto(retAcct);
    }

    @Override
    public AccountDTO findById(Long acctId) {
        return accountRepository.findById(acctId)
                .map(accountMapper::accountToDto)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(acctId));
    }

    @Override
    public AccountDTO manageAccountStatus(Long acctId, AccountStatus acctStatus) {
        Account acct = accountRepository.findById(acctId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(acctId));
        acct.setAccountStatus(acctStatus);
        return accountMapper.accountToDto(accountRepository.save(acct));
    }

    @Transactional
    public AccountHistory generateAccountHistoryRecord(Account account, AccountEvent event) {
        AccountHistory acctHistory = new AccountHistory();
        acctHistory.setAccount(account);
        acctHistory.setAccountEvent(event);
        acctHistory.setDateTime(LocalDateTime.now());
        return accountHistoryRepository.save(acctHistory);
    }
}