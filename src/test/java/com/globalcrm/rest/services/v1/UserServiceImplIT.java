package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceImplIT {

    public static final String FIRST_NAME = "FIRST ";
    public static final String LAST_NAME = "LAST ";
    public static final String WEBSITE = "www.company.com";
    public static Long ACCT_ID;

    UserService userService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;


    @Before
    public void setUp() {
        userService = new UserServiceImpl(userRepository, accountRepository);

        Account acct = new Account();
        acct.setName("TEST");
        acct.setWebsite("www.company.com");
        acct.setCreationDateTime(LocalDateTime.now());
        acct.setSubscriptionType(SubscriptionType.MICRO);

        User holder = new User();
        holder.setEmail("holder@globalcrm.com");
        holder.setAccount(acct);
        acct.setAccountHolder(holder);

        Account acctSaved = accountRepository.saveAndFlush(acct);
        ACCT_ID = acctSaved.getId();
        log.info("Account creationDateTime, ID: " + ACCT_ID);
    }

    @Test
    public void createUserTest() {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(FIRST_NAME);
        userDTO.setLastName(LAST_NAME);
        userDTO.setEmail("user@globalcrm.com");

        UserDTO retUser = userService.createAccountUser(ACCT_ID, userDTO);

        log.info(retUser.toString());
        assertNotNull(retUser.getId());
        assertNotNull(retUser.getAccount());
        assertEquals(ACCT_ID, retUser.getAccount().getId());

    }
}
