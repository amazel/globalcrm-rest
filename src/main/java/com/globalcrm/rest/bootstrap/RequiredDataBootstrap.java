package com.globalcrm.rest.bootstrap;

import com.globalcrm.rest.api.v1.model.*;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.services.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    @Transactional
    public void run(String... args) {
        AccountDTO acct = loadDefaultAccount();
        UserDTO usr = addUser(acct.getId());
        setUserPassword(acct.getAccountHolder(), "password");
        CompanyDTO companyDTO = createDummyCompany(acct.getId());

        ContactDTO createdContact = createDummyContact(acct.getId(), companyDTO.getId(),  usr.getId(),"Contacto Uno");
        createSale(acct.getId(), usr.getId(), createdContact.getId());
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Contacto Dos");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Contacto Tres");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Otro Uno");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Another One");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Contact");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Maximiliano");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Pedro");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Mario");
        createDummyContact(acct.getId(), companyDTO.getId(), usr.getId(), "Jose ");
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

    private ContactDTO createDummyContact(Long accId, Long companyId, Long userId, String name) {
        log.info("Creating contact {}", name);

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(name);
        contactDTO.setLastNames("Last Names");
        contactDTO.setVisibleFor(VisibleFor.ALL);
        contactDTO.setContactType(ContactType.LEAD);
        Set<PhoneDTO> phones = new HashSet<>();
        Set<EmailDTO> emails = new HashSet<>();
        phones.add(new PhoneDTO(null, null, PhoneType.FAX, genPhone()));
        phones.add(new PhoneDTO(null, null, PhoneType.WORK, genPhone()));
        phones.add(new PhoneDTO(null, null, PhoneType.WORK, genPhone()));
        contactDTO.setPhones(phones);
        emails.add(new EmailDTO(null, null, EmailType.PERSONAL, "correo@personal.com"));
        emails.add(new EmailDTO(null, null, EmailType.WORK, "correo@work.com"));
        emails.add(new EmailDTO(null, null, EmailType.WORK, "correo2@work.com"));
        contactDTO.setEmails(emails);


        return contactService.createContact(accId, companyId, userId, contactDTO);
    }

    private SaleDTO createSale(Long accountId, Long userId, Long contactId) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setDescription("Sale Description");
        saleDTO.setTitle("Sale Title");
        return saleService.createNewSale(accountId, userId, contactId, saleDTO);
    }

    private String genPhone() {
        Random r = new Random();
        Integer i = r.nextInt((99999999 - 11111111) + 1) + 11111111;
        return i.toString();
    }
}
