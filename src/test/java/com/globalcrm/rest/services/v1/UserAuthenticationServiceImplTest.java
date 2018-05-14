package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.UserAuthDTO;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.UserRepository;
import com.globalcrm.rest.security.JwtTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserAuthenticationServiceImplTest {
    public static final Long USER_ID = 122L;
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "SGDFGSGG";
    UserAuthenticationService userAuthenticationService;

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtTokenUtil jwtTokenUtil;
    @Mock
    AuthenticationManager authenticationManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userAuthenticationService = new UserAuthenticationServiceImpl(userRepository, passwordEncoder, jwtTokenUtil, authenticationManager);
    }

    @Test
    public void setUserPassword() {
        User user = new User();
        user.setEmail(EMAIL);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(anyString())).thenReturn(PASSWORD);
        user.setPassword(PASSWORD);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserAuthDTO userAuthDTO = userAuthenticationService.setUserPassword(EMAIL,PASSWORD);

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(anyString());
    }

    @Test
    public void login() {
        String token = "generatedToken";
        User user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(jwtTokenUtil.generateToken(any())).thenReturn(token);

        UserAuthDTO userAuthDTO = userAuthenticationService.login(EMAIL, PASSWORD);

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(jwtTokenUtil, times(1)).generateToken(any(UserAuthDTO.class));
        assertEquals(token, userAuthDTO.getJwtToken());
    }
}