package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.mapper.UserMapper;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.User;
import com.globalcrm.rest.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    UserMapper mapper = UserMapper.INSTANCE;

    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, mapper);
    }

    @Test
    public void getAllUsers() {
        //Given
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        //When
        List<UserDTO> users = userService.getAllUsers();

        //Then
        assertEquals(2, users.size());
    }
}