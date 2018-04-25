package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.EmailType;
import com.globalcrm.rest.domain.PhoneType;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.VisibleFor;
import com.globalcrm.rest.services.v1.AccountService;
import com.globalcrm.rest.services.v1.CompanyService;
import com.globalcrm.rest.services.v1.ContactService;
import com.globalcrm.rest.services.v1.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@Component
public class RequiredDataBootstrap implements CommandLineRunner {

    AccountService accountService;
    UserService userService;
    CompanyService companyService;
    ContactService contactService;

    public RequiredDataBootstrap(AccountService accountService, UserService userService, CompanyService companyService, ContactService contactService) {
        this.accountService = accountService;
        this.userService = userService;
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) {
        AccountDTO acct = loadDefaultAccount();
        addUser(acct.getId());
        CompanyDTO companyDTO = createDummyCompany(acct.getId());
        createDummyContact(acct.getId(), companyDTO.getId());
    }

    public AccountDTO loadDefaultAccount() {
        log.info("Loading default account");

        AccountDTO acct = new AccountDTO();
        acct.setName("TEST");
        acct.setWebsite("www.company.com");
        acct.setSubscriptionType(SubscriptionType.MICRO);

        UserDTO holder = new UserDTO();
        holder.setFirstName("ACCOUNT");
        holder.setLastName("HOLDER");
        holder.setEmail("EMAIL");

        acct.setAccountHolder(holder);

        return accountService.createAccount(acct);
    }

    public UserDTO addUser(Long acctId) {
        UserDTO u1 = new UserDTO();
        u1.setFirstName("USER");
        u1.setLastName("ONE");
        u1.setEmail("EMAIL2");

        return userService.createAccountUser(acctId, u1);
    }

    private CompanyDTO createDummyCompany(Long accId) {
        CompanyDTO company = new CompanyDTO();
        company.setName("DUMMY COMPANY");
        company.setZipCode("ZIP");
        company.setVisibleFor(VisibleFor.ALL);
        company.setState("STATE");
        company.setCity("CITY");
        company.setAddress("ADDRESS");

        return companyService.createCompany(accId, company);
    }

    private void createDummyContact(Long acctId ,Long companyId) {
        log.info("Creating Dummy ContactDTO");

        ContactDTO contact = new ContactDTO();
        contact.setNames("Mi Contacto");
        contact.setLastNames("Dummy Last Names");
        contact.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().put(PhoneType.HOME, "5550879089");
        contact.getPhones().put(PhoneType.MOBILE, "555087459");
        contact.getEmails().put(EmailType.PERSONAL, "correo@personal.com");
        contact.getEmails().put(EmailType.WORK, "correo@trabajo.com");

        contactService.createContact(acctId, companyId, contact);
    }


    /*
    private void createDummyContact(Account account) {
        log.info("Creating Dummy Contact");

        Contact contact = new Contact();
        Company company = new Company();
        company.setName("DUMMY COMPANY");
        company.setAccount(account);
        contact.setCompany(company);
        contact.setNames("Mi Contacto");
        Map<PhoneType, String> phones = new HashMap<>();
        phones.put(PhoneType.HOME, "5550879089");
        phones.put(PhoneType.MOBILE, "555087459");
        contact.setPhones(phones);

        contact.setVisibleFor(VisibleFor.ALL);
        contact.setLastNames("Dummy Last Names");
        contact.getEmails().put(EmailType.PERSONAL,"correo@personal.com");
        contact.getEmails().put(EmailType.WORK,"correo@trabajo.com");
        contactRepository.save(contact);
    }

    public Account loadDefaultAccount() {
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
        holder.setEmail("EMAIL");
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
        u1.setEmail("EMAIL2");
        acctSaved.addUser(u1);
        return accountRepository.save(acct);
    }
    */
}
