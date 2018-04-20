package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Component
public class RequiredDataBootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public RequiredDataBootstrap(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) {
        Long actId = loadDefaultAccount();
    }

    public Long loadDefaultAccount() {
        log.info("Loading default account");

        Account acct = new Account();
        acct.setCompanyName("Default");
        acct.setCreationDateTime(LocalDateTime.now());
        acct.setSubscriptionType(SubscriptionType.MICRO);

        Account acctSaved = accountRepository.save(acct);
        return acctSaved.getId();
    }
}
