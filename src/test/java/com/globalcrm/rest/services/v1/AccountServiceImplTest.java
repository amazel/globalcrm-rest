package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.AccountHistoryRepository;
import com.globalcrm.rest.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {
    public static final Long ID = 1L;
    public static final String NAME = "Company";
    public static final LocalDateTime CREATION_TIME = LocalDateTime.now();
    public static final String WEBSITE = "www.company.com";
    public static final String USER_NAME = "Holder";
    public static final Long USER_ID = 2L;

    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountHistoryRepository accountHistoryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountRepository,accountHistoryRepository);
    }


    @Test
    public void createAccount() {
        //Given
        when(accountRepository.save(any(Account.class))).thenReturn(createDummyAccount());

        //When
        AccountDTO accountDTO = accountService.createAccount(createDummyAccountDTO());

        //Then
        assertEquals(ID, accountDTO.getId());
        assertEquals(NAME, accountDTO.getName());
        assertEquals(SubscriptionType.MEDIUM, accountDTO.getSubscriptionType());
        assertEquals(USER_ID, accountDTO.getAccountHolder().getId());
        assertEquals(AccountStatus.NEW, accountDTO.getAccountStatus());
        assertEquals(WEBSITE, accountDTO.getWebsite());
        assertEquals(1, accountDTO.getUsers().size());
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    public void findById() {

        //Given
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(createDummyAccount()));

        //When
        AccountDTO accountDTO = accountService.findDTOById(ID);

        //Then
        assertEquals(ID, accountDTO.getId());
        assertEquals(NAME, accountDTO.getName());
        assertEquals(SubscriptionType.MEDIUM, accountDTO.getSubscriptionType());
        assertEquals(USER_ID, accountDTO.getAccountHolder().getId());
        assertEquals(AccountStatus.NEW, accountDTO.getAccountStatus());
        assertEquals(WEBSITE, accountDTO.getWebsite());
        assertEquals(1, accountDTO.getUsers().size());
        verify(accountRepository, times(1)).findById(anyLong());
    }

    @Test
    public void manageAccountStatus() {
        //Given
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(createDummyAccount()));
        Account account = createDummyAccount();
        account.setAccountStatus(AccountStatus.CANCELLED);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        //When
        AccountDTO accountDTO = accountService.manageAccountStatus(ID,AccountStatus.CANCELLED);

        //Then
        assertEquals(AccountStatus.CANCELLED, accountDTO.getAccountStatus());
        verify(accountRepository, times(1)).findById(anyLong());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void generateAccountHistoryRecord() {
        //Given
        Account account = createDummyAccount();
        AccountHistory acctHistory = new AccountHistory();
        acctHistory.setAccount(account);
        acctHistory.setAccountEvent(AccountEvent.BILLED);
        acctHistory.setDateTime(LocalDateTime.now());
        when(accountHistoryRepository.save(any(AccountHistory.class))).thenReturn(acctHistory);

        //When
        AccountHistory accountHistory = accountService.generateAccountHistoryRecord(account,AccountEvent.BILLED);

        //Then
        assertEquals(AccountEvent.BILLED, accountHistory.getAccountEvent());
        assertEquals(ID, accountHistory.getAccount().getId());
        verify(accountHistoryRepository, times(1)).save(any(AccountHistory.class));
    }


    public AccountDTO createDummyAccountDTO(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ID);
        accountDTO.setName(NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(USER_NAME);
        userDTO.setId(USER_ID);
        accountDTO.setAccountHolder(userDTO);
        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(WEBSITE);
        Set<UserDTO> users = new HashSet<>();
        users.add(new UserDTO());
        accountDTO.setUsers(users);
        return accountDTO;
    }

    public Account createDummyAccount() {
        Account account = new Account();
        account.setId(ID);
        account.setName(NAME);
        account.setSubscriptionType(SubscriptionType.MEDIUM);
        User user = new User();
        user.setFirstName(USER_NAME);
        user.setId(USER_ID);
        account.setAccountHolder(user);
        account.setCreationDateTime(CREATION_TIME);
        account.setAccountStatus(AccountStatus.NEW);
        account.setWebsite(WEBSITE);
        Set<User> users = new HashSet<>();
        users.add(new User());
        account.setUsers(users);
        return account;
    }
}