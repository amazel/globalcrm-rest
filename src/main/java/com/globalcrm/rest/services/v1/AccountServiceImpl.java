package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.AccountMapper;
import com.globalcrm.rest.api.v1.mapper.CycleAvoidingMappingContext;
import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.AccountEvent;
import com.globalcrm.rest.domain.AccountHistory;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.exceptions.ExceptionFactory;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        Account savedAcct = accountRepository.save(accountMapper.accountDtoToAccount(accountDTO));
        generateAccountHistoryRecord(savedAcct, AccountEvent.CREATED);
        return accountMapper.accountToAccountDto(savedAcct);
    }

    @Override
    public AccountDTO findById(Long acctId) {
        return accountRepository.findById(acctId)
                .map(account -> accountMapper.accountToAccountDto(account))
                .orElseThrow(() -> ExceptionFactory.accountNotFound(acctId));
    }
    @Override
    public AccountDTO manageAccountStatus(Long acctId, AccountStatus acctStatus) {
        Account acct = accountRepository.findById(acctId)
                .orElseThrow(() -> ExceptionFactory.accountNotFound(acctId));
        acct.setAccountStatus(acctStatus);
        return accountMapper.accountToAccountDto(accountRepository.save(acct));
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
