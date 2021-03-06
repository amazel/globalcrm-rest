package com.globalcrm.rest.api.v1.mapper;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@Slf4j
public class AccountMapperTest {

    public static final Long ID = 1L;
    public static final String COMPANY_NAME = "Company";
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
        account.setAccountStatus(AccountStatus.NEW);
        account.setWebsite(COMPANY_WEBSITE);
        Set<User> users = new HashSet<>();

        User user2 = new User();
        user2.setFirstName(USER_NAME);
        user2.setId(3L);
        user2.setAccount(account);

        users.add(user2);
        account.setUsers(users);

        log.info(account.toString());
        AccountDTO accountDTO = accountMapper.accountToDto(account);
        log.info(accountDTO.toString());
        assertEquals(ID, accountDTO.getId());
        assertEquals(COMPANY_NAME, accountDTO.getName());
        assertEquals(SubscriptionType.MEDIUM, accountDTO.getSubscriptionType());
        assertEquals(USER_ID, accountDTO.getAccountHolder().getId());
        assertEquals(AccountStatus.NEW, accountDTO.getAccountStatus());
        assertEquals(COMPANY_WEBSITE, accountDTO.getWebsite());
        assertEquals(1, accountDTO.getUsers().size());
        // assertNull(accountDTO.getAccountHolder().getAccount().getAccountHolder());
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
        userDTO.setAccount(accountDTO);

        accountDTO.setAccountHolder(userDTO);

        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(COMPANY_WEBSITE);


        UserDTO user2 = new UserDTO();
        user2.setFirstName(USER_NAME);
        user2.setId(3L);
        user2.setAccount(accountDTO);


        accountDTO.getUsers().add(user2);

        Account account = accountMapper.dtoToAccount(accountDTO);

        log.info(account.toString());

        assertEquals(ID, account.getId());
        assertEquals(COMPANY_NAME, account.getName());
        assertEquals(SubscriptionType.MEDIUM, account.getSubscriptionType());
        assertEquals(USER_ID, account.getAccountHolder().getId());
        assertEquals(AccountStatus.NEW, account.getAccountStatus());
        assertEquals(COMPANY_WEBSITE, account.getWebsite());
        assertEquals(1, account.getUsers().size());
    }
}