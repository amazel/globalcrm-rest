package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@Slf4j
public class UserServiceImplTest {
    public static final String NAME = "Test Name";
    public static final String LAST_NAME = "Test LastName";
    public static final String EMAIL = "test@email.com";
    public static final Long USER_ID = 1L;
    public static final Long ACCT_ID = 1L;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, accountRepository);
    }

    @Test
    public void getAllUsers() {
        //Given
        Set<User> mockUsers = new HashSet<>();
        mockUsers.add(new User());
        Account mockAcct = new Account();
        mockAcct.setId(ACCT_ID);
        mockAcct.setUsers(mockUsers);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(mockAcct));

        //When
        List<UserDTO> users = userService.getAllUsers(ACCT_ID);

        //Then
        assertEquals(1, users.size());
    }

    @Test
    public void getUserById() {
        //Given
        User mockUser = new User();
        mockUser.setId(USER_ID);
        mockUser.setEmail(EMAIL);

        Account account = new Account();
        account.setId(ACCT_ID);
        account.getUsers().add(mockUser);


        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        //When
        UserDTO userDTO = userService.getAccountUserById(ACCT_ID, USER_ID);

        //Then
        assertEquals(USER_ID, userDTO.getId());
        verify(accountRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveUser() {
        //Given
        User savedUser = new User();
        savedUser.setId(USER_ID);
        savedUser.setEmail(EMAIL);

        Account retAccount = new Account();
        retAccount.setId(ACCT_ID);
        savedUser.setAccount(retAccount);
        retAccount.getUsers().add(savedUser);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(retAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(retAccount);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(EMAIL);

        //When
        UserDTO retUser = userService.createAccountUser(ACCT_ID, userDTO);

        log.info(retUser.toString());

        //Then
       // assertEquals(USER_ID, retUser.getId());
        verify(accountRepository, times(1)).findById(anyLong());
        verify(accountRepository, times(1)).save(any(Account.class));
    }
}