package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UserMapperTest {

    public static final Long USER_ID = 2L;
    public static final String USER_NAME = "UserT";
    public static final String USER_LASTNAME = "Holsder";
    public static final Long ACCT_ID = 1L;
    public static final String COMPANY_NAME = "Company";
    public static final LocalDateTime CREATION_TIME = LocalDateTime.now();
    public static final String COMPANY_WEBSITE = "www.company.com";

    UserMapper mapper = UserMapper.INSTANCE;

    @Test
    public void userDtoToUser() {
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setFirstName(USER_NAME);
        userDTO1.setLastName(USER_LASTNAME);
        userDTO1.setId(USER_ID);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCT_ID);
        accountDTO.setCompanyName(COMPANY_NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        accountDTO.setCreationDateTime(CREATION_TIME);
        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setCompanyWebsite(COMPANY_WEBSITE);
        accountDTO.setAccountHolder(userDTO1);

        Set<UserDTO> users = new HashSet<>();

        UserDTO user2 = new UserDTO();
        user2.setFirstName(USER_NAME);
        user2.setId(3L);
        user2.setAccount(accountDTO);

        users.add(user2);
        accountDTO.setUsers(users);

        userDTO1.setAccount(accountDTO);

        User user = mapper.userDtoToUser(userDTO1);

        assertEquals(USER_ID, user.getId());
        assertEquals(USER_NAME, user.getFirstName());
        assertEquals(USER_LASTNAME, user.getLastName());

        assertEquals(COMPANY_NAME, user.getAccount().getCompanyName());
        assertEquals(AccountStatus.NEW, user.getAccount().getAccountStatus());

        assertNull(user.getAccount().getAccountHolder().getAccount());
        assertTrue(user.getAccount().getUsers().isEmpty());
    }

    @Test
    public void userToUserDto() {
        User user = new User();
        user.setFirstName(USER_NAME);
        user.setLastName(USER_LASTNAME);
        user.setId(USER_ID);

        Account account = new Account();
        account.setId(ACCT_ID);
        account.setCompanyName(COMPANY_NAME);
        account.setSubscriptionType(SubscriptionType.MEDIUM);
        account.setCreationDateTime(CREATION_TIME);
        account.setAccountStatus(AccountStatus.NEW);
        account.setCompanyWebsite(COMPANY_WEBSITE);
        account.setAccountHolder(user);

        Set<User> users = new HashSet<>();

        User user2 = new User();
        user2.setFirstName(USER_NAME);
        user2.setId(3L);
        user2.setAccount(account);

        users.add(user2);
        account.setUsers(users);

        user.setAccount(account);

        UserDTO userDTO = mapper.userToUserDto(user);

        assertEquals(USER_ID, userDTO.getId());
        assertEquals(USER_NAME, userDTO.getFirstName());
        assertEquals(USER_LASTNAME, userDTO.getLastName());

        assertEquals(COMPANY_NAME, userDTO.getAccount().getCompanyName());
        assertEquals(AccountStatus.NEW, userDTO.getAccount().getAccountStatus());

        assertNull(userDTO.getAccount().getAccountHolder().getAccount());
        assertTrue(userDTO.getAccount().getUsers().isEmpty());
    }
}