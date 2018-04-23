package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Component
public class RequiredDataBootstrap implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;

    public RequiredDataBootstrap(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
    }

    @Override
    public void run(String... args) {
        loadDefaultAccount();
    }

    public void loadDefaultAccount() {
        log.info("Loading default account");

        Account acct = new Account();
        acct.setName("TEST");
        acct.setWebsite("www.company.com");
        acct.setCreationDateTime(LocalDateTime.now());
        acct.setSubscriptionType(SubscriptionType.MICRO);

        User holder = new User();
        holder.setAccount(acct);
        holder.setFirstName("ACCOUNT");
        holder.setLastName("HOLDER");
        //userRepository.save(holder);
        acct.setAccountHolder(holder);

        Account acctSaved = accountRepository.save(acct);

        AccountHistory acctHistory = new AccountHistory();
        acctHistory.setAccount(acctSaved);
        acctHistory.setAccountEvent(AccountEvent.CREATED);
        acctHistory.setDateTime(LocalDateTime.now());
        accountHistoryRepository.save(acctHistory);

        User u1 = new User();
        u1.setFirstName("USER");
        u1.setLastName("ONE");
        acctSaved.addUser(u1);
        accountRepository.save(acct);
    }
}
