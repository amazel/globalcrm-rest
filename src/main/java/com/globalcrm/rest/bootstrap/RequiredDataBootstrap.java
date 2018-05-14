package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.EmailType;
import com.globalcrm.rest.domain.PhoneType;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.VisibleFor;
import com.globalcrm.rest.services.v1.*;
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
    final UserAuthenticationService userAuthenticationService;
    CompanyService companyService;
    ContactService contactService;
    final SaleService saleService;

    public RequiredDataBootstrap(AccountService accountService, UserService userService, UserAuthenticationService userAuthenticationService, CompanyService companyService, ContactService contactService, SaleService saleService) {
        this.accountService = accountService;
        this.userService = userService;
        this.userAuthenticationService = userAuthenticationService;
        this.companyService = companyService;
        this.contactService = contactService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) {
        AccountDTO acct = loadDefaultAccount();
        UserDTO usr = addUser(acct.getId());
        setUserPassword(acct.getAccountHolder(), "password");
        CompanyDTO companyDTO = createDummyCompany(acct.getId());
        ContactDTO createdContact = createDummyContact(acct.getId(), companyDTO.getId());
        createSale(acct.getId(),usr.getId(),createdContact.getId());

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
        holder.setEmail("holder@mail.com");

        acct.setAccountHolder(holder);

        return accountService.createAccount(acct);
    }

    public UserDTO addUser(Long acctId) {
        UserDTO u1 = new UserDTO();
        u1.setFirstName("USER");
        u1.setLastName("ONE");
        u1.setEmail("user1@mail.com");

        return userService.createAccountUser(acctId, u1);
    }

    private void setUserPassword(UserDTO usr, String password) {
        userAuthenticationService.setUserPassword(usr.getEmail(), password);
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

    private ContactDTO createDummyContact(Long acctId, Long companyId) {
        log.info("Creating Dummy ContactDTO");

        ContactDTO contact = new ContactDTO();
        contact.setNames("Mi Contacto");
        contact.setLastNames("Dummy Last Names");
        contact.setVisibleFor(VisibleFor.ALL);
        contact.getPhones().put(PhoneType.HOME, "5550879089");
        contact.getPhones().put(PhoneType.MOBILE, "555087459");
        contact.getEmails().put(EmailType.PERSONAL, "correo@personal.com");
        contact.getEmails().put(EmailType.WORK, "correo@trabajo.com");

        return contactService.createContact(acctId, companyId, contact);
    }

    private SaleDTO createSale(Long accountId, Long userId, Long contactId) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setDescription("Sale Description");
        saleDTO.setTitle("Sale Title");
        return saleService.createNewSale(accountId, userId, contactId, saleDTO);
    }
}
