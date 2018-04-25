package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.UserDTO;
import com.globalcrm.rest.domain.AccountStatus;
import com.globalcrm.rest.domain.SubscriptionType;
import com.globalcrm.rest.exceptions.RestResponseEntityExceptionHandler;
import com.globalcrm.rest.services.v1.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static com.globalcrm.rest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class AccountControllerTest {
    public static final Long ACCT_ID = 1L;
    public static final String COMPANY_NAME = "Company";
    public static final LocalDateTime CREATION_TIME = LocalDateTime.now();
    public static final String COMPANY_WEBSITE = "www.company.com";
    public static final String USER_NAME = "UserT";
    public static final Long USER_ID = 2L;

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService accountService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAccountById() throws Exception {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(ACCT_ID);
        when(accountService.findById(anyLong())).thenReturn(accountDTO);

        mockMvc.perform(get(AccountController.BASE_URL + "/" + ACCT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    public void createAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName(COMPANY_NAME);
        accountDTO.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(USER_NAME);
        accountDTO.setAccountHolder(userDTO);
        // accountDTO.setCreationDateTime(CREATION_TIME);
        accountDTO.setAccountStatus(AccountStatus.NEW);
        accountDTO.setWebsite(COMPANY_WEBSITE);

        AccountDTO retAcct = new AccountDTO();

        retAcct.setId(ACCT_ID);
        retAcct.setName(COMPANY_NAME);
        retAcct.setSubscriptionType(SubscriptionType.MEDIUM);
        UserDTO retUser = new UserDTO();
        retUser.setFirstName(USER_NAME);
        retUser.setId(USER_ID);
        retAcct.setAccountHolder(retUser);
        retAcct.setCreationDateTime(CREATION_TIME);
        retAcct.setAccountStatus(AccountStatus.NEW);
        retAcct.setWebsite(COMPANY_WEBSITE);

        when(accountService.createAccount(any(AccountDTO.class))).thenReturn(retAcct);

        log.error(asJsonString(retAcct));
        mockMvc.perform(post(AccountController.BASE_URL+"/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
}