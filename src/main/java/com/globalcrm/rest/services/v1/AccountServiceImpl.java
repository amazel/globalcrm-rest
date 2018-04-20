package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
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
    private UserRepository userRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Account createAccount() {
        log.info("CREATING ACCOUNT");
        Account acct = new Account();
        acct.setCompanyName("TEST");
        acct.setCreationDateTime(LocalDateTime.now());
        acct.setSubscriptionType(SubscriptionType.MICRO);
        Account acctSaved = accountRepository.save(acct);

        genererateAccountHistoryRecord(acctSaved,AccountEvent.CREATED);

        User holder = new User();
        holder.setAccount(acctSaved);
        userRepository.save(holder);
        acctSaved.setAccountHolder(holder);
        acctSaved.addUser(new User());
        accountRepository.save(acct);
        return acctSaved;
    }

    private AccountHistory genererateAccountHistoryRecord(Account account, AccountEvent event){
        AccountHistory acctHistory = new AccountHistory();
        acctHistory.setAccount(account);
        acctHistory.setAccountEvent(event);
        acctHistory.setDateTime(LocalDateTime.now());
        return accountHistoryRepository.save(acctHistory);
    }
}
