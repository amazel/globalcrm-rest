package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.bootstrap.RequiredDataBootstrap;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceImplIT {

    public static final String NAME = "Company";
    public static final String WEBSITE = "www.company.com";
    public static final String USER_NAME = "Holder";
    public static final String USER_EMAIL = "holder@global.com";
    public static final String USER_EMAIL2 = "user@global.com";

    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    ContactRepository contactRepository;

    @Before
    public void setUp()  {

            accountService = new AccountServiceImpl(accountRepository, accountHistoryRepository);
    }

    @Test
    public void createAccountTest() {

        AccountDTO retAcct = accountService.createAccount(createDummyAccountDTO());

        log.info(retAcct.toString());

        assertNotNull(retAcct.getCreationDateTime());
        assertNotNull(retAcct.getId());
        assertEquals(1, retAcct.getUsers().size());
    }

    private AccountDTO createDummyAccountDTO() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName(NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(USER_NAME);
        userDTO.setEmail(USER_EMAIL);
        accountDTO.setAccountHolder(userDTO);

        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(WEBSITE);
        UserDTO user1 = new UserDTO();
        user1.setEmail("USER_EMAIL2");
        accountDTO.getUsers().add(user1);
        return accountDTO;
    }

}
