package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.exceptions.RestResponseEntityExceptionHandler;
import com.globalcrm.rest.exceptions.ResourceNotFoundException;
import com.globalcrm.rest.services.v1.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest extends AbstractRestControllerTest{
    public static final String NAME = "Test Name";
    public static final String LAST_NAME = "Test LastName";
    public static final String EMAIL = "test@email.com";
    public static final Long USER_ID = 1L;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserDTO> users = Arrays.asList(new UserDTO(), new UserDTO());

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get(UserController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)));
    }

    @Test
    public void getUserById() throws Exception{
        //Given
        UserDTO mockUser = new UserDTO();
        mockUser.setId(USER_ID);
        mockUser.setFirstName(NAME);
        when(userService.getUserById(anyLong())).thenReturn(mockUser);

        mockMvc.perform(get(UserController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(NAME)));
    }


    @Test
    public void createNewUser() throws Exception{
        //Given
        UserDTO newUser = new UserDTO();
        newUser.setFirstName(NAME);
        newUser.setLastName(LAST_NAME);
        newUser.setEmail(EMAIL);

        UserDTO retUser = new UserDTO();
        retUser.setFirstName(NAME);
        retUser.setLastName(LAST_NAME);
        retUser.setEmail(EMAIL);
        retUser.setId(USER_ID);

        when(userService.saveUser(any(UserDTO.class))).thenReturn(retUser);

        //When & then
        mockMvc.perform(
                post(UserController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void updateUser() throws Exception{
        //Given
        UserDTO newUser = new UserDTO();
        newUser.setFirstName(NAME);
        newUser.setLastName(LAST_NAME);
        newUser.setEmail(EMAIL);
        newUser.setId(USER_ID);

        UserDTO retUser = new UserDTO();
        retUser.setFirstName(NAME);
        retUser.setLastName(LAST_NAME);
        retUser.setEmail(EMAIL);
        retUser.setId(USER_ID);

        when(userService.updateUser(anyLong(),any(UserDTO.class))).thenReturn(retUser);

        //When & then
        mockMvc.perform(
                put(UserController.BASE_URL+"/"+USER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(NAME)))
                .andExpect(jsonPath("$.lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));

    }

    @Test
    public void deleteUser() throws Exception{
        mockMvc.perform(delete(UserController.BASE_URL + "/"+USER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userService).deleteUser(anyLong());
    }


    @Test
    public void getUserByIdNotFound() throws Exception {

        when(userService.getUserById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(UserController.BASE_URL + "/222")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getUserByIdBadRequest() throws Exception {

        mockMvc.perform(get(UserController.BASE_URL + "/WWW")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}