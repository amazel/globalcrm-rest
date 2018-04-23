package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.bootstrap.RequiredDataBootstrap;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
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

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    ContactRepository contactRepository;

    @Before
    public void setUp() throws Exception {

        //setup data for testing
        RequiredDataBootstrap bootstrap = new RequiredDataBootstrap(accountRepository, accountHistoryRepository, contactRepository);
        bootstrap.run();

        accountService = new AccountServiceImpl(accountRepository, accountHistoryRepository);
    }

    @Test
    public void createAccountTest(){

        AccountDTO retAcct = accountService.createAccount(createDummyAccountDTO());

        log.info(retAcct.toString());

        assertNotNull(retAcct.getCreationDateTime());
        assertNotNull(retAcct.getId());
        assertEquals(1, retAcct.getUsers().size());
    }

    @Test
    public void  findAccountById(){

    }

    private AccountDTO createDummyAccountDTO(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName(NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(USER_NAME);

        accountDTO.setAccountHolder(userDTO);

        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(WEBSITE);
        Set<UserDTO> users = new HashSet<>();
        users.add(new UserDTO());
        accountDTO.setUsers(users);
        return accountDTO;
    }

}
