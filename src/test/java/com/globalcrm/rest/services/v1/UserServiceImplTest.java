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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@Slf4j
public class UserServiceImplTest {
    public static final String EMAIL = "test@email.com";
    public static final Long USER_ID = 1L;
    public static final Long ACCT_ID = 1L;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, accountRepository);
    }

    @Test
    public void createAccountUser() {
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

    @Test
    public void getUserById() {

        Account retAccount = new Account();
        retAccount.setId(ACCT_ID);
        User savedUser = new User();
        savedUser.setId(USER_ID);
        savedUser.setEmail(EMAIL);
        savedUser.setAccount(retAccount);
        retAccount.getUsers().add(savedUser);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(savedUser));

        UserDTO userDTO = userService.getUserById(USER_ID);

        assertEquals(USER_ID, userDTO.getId());
        verify(userRepository, times(1)).findById(anyLong());
    }
}