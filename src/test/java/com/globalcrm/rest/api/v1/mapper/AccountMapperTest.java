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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountMapperTest {

    public static final Long ID = 1L;
    public static final String COMPANY_NAME = "Company";
    public static final LocalDateTime CREATION_TIME = LocalDateTime.now();
    public static final String COMPANY_WEBSITE = "www.company.com";
    public static final String USER_NAME = "UserT";
    public static final Long USER_ID = 2L;
    AccountMapper accountMapper = AccountMapper.INSTANCE;


    @Test
    public void accountDtoToAccount() {
        Account account = new Account();
        account.setId(ID);
        account.setName(COMPANY_NAME);
        account.setSubscriptionType(SubscriptionType.MEDIUM);
        User user = new User();
        user.setFirstName(USER_NAME);
        user.setId(USER_ID);
        user.setAccount(account);
        account.setAccountHolder(user);
        account.setCreationDateTime(CREATION_TIME);
        account.setAccountStatus(AccountStatus.NEW);
        account.setWebsite(COMPANY_WEBSITE);
        Set<User> users = new HashSet<>();

        User user2 = new User();
        user2.setFirstName(USER_NAME);
        user2.setId(3L);
        user2.setAccount(account);

        users.add(user2);
        account.setUsers(users);

        AccountDTO accountDTO = accountMapper.accountToAccountDto(account);

        assertEquals(ID, accountDTO.getId());
        assertEquals(COMPANY_NAME, accountDTO.getName());
        assertEquals(SubscriptionType.MEDIUM, accountDTO.getSubscriptionType());
        assertEquals(USER_ID, accountDTO.getAccountHolder().getId());
        assertEquals(CREATION_TIME, accountDTO.getCreationDateTime());
        assertEquals(AccountStatus.NEW, accountDTO.getAccountStatus());
        assertEquals(COMPANY_WEBSITE, accountDTO.getWebsite());
        assertEquals(1, accountDTO.getUsers().size());
        assertNull(accountDTO.getAccountHolder().getAccount().getAccountHolder());
    }

    @Test
    public void accountToAccountDto() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ID);
        accountDTO.setName(COMPANY_NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(USER_NAME);
        userDTO.setId(USER_ID);
        accountDTO.setAccountHolder(userDTO);
        accountDTO.setCreationDateTime(CREATION_TIME);
        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(COMPANY_WEBSITE);
        Set<UserDTO> users = new HashSet<>();
        users.add(new UserDTO());
        accountDTO.setUsers(users);

        Account account = accountMapper.accountDtoToAccount(accountDTO);

        assertEquals(ID, account.getId());
        assertEquals(COMPANY_NAME, account.getName());
        assertEquals(SubscriptionType.MEDIUM, account.getSubscriptionType());
        assertEquals(USER_ID, account.getAccountHolder().getId());
        assertEquals(CREATION_TIME, account.getCreationDateTime());
        assertEquals(AccountStatus.NEW, account.getAccountStatus());
        assertEquals(COMPANY_WEBSITE, account.getWebsite());
        assertEquals(1, account.getUsers().size());
    }
}