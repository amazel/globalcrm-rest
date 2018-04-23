package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Component
public class RequiredDataBootstrap implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final AccountHistoryRepository accountHistoryRepository;
    private final ContactRepository contactRepository;

    public RequiredDataBootstrap(AccountRepository accountRepository, AccountHistoryRepository accountHistoryRepository, ContactRepository contactRepository) {
        this.accountRepository = accountRepository;
        this.accountHistoryRepository = accountHistoryRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void run(String... args) {
        createDummyContact();
        loadDefaultAccount();

    }

    private void createDummyContact() {
        log.info("Creating Dummy Contact");

        Contact contact = new Contact();
        Company company = new Company();
        company.setName("DUMMY COMPANY");
        contact.setCompany(company);
        contact.setNames("Mi Contacto");
        Map<PhoneType,String> phones = new HashMap<>();
        phones.put(PhoneType.HOME,"5550879089");
        phones.put(PhoneType.MOBILE,"555087459");
        contact.setPhones(phones);
        contactRepository.save(contact);
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
