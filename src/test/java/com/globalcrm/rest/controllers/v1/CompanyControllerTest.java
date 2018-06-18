package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.AccountDTO;
import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.exceptions.RestResponseEntityExceptionHandler;
import com.globalcrm.rest.services.v1.AccountService;
import com.globalcrm.rest.services.v1.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.globalcrm.rest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyControllerTest {
    public static final Long ACCT_ID = 1L;
    private static final String COMPANY = "COMPANY NAME";

    @InjectMocks
    CompanyController companyController;

    @Mock
    CompanyService companyService;

    @Mock
    AccountService accountService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getCompanies() throws Exception {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.getCompanies().add(new CompanyDTO());

        when(accountService.findDTOById(anyLong())).thenReturn(accountDTO);

        mockMvc.perform(
                get(CompanyController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void createNewCompany() throws Exception {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(COMPANY);

        when(companyService.createCompany(anyLong(), any(CompanyDTO.class))).thenReturn(companyDTO);

        //When & then
        mockMvc.perform(
                post(CompanyController.BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString())
                        .content(asJsonString(companyDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(COMPANY)));
    }

    @Test
    public void getAccountCompany() throws Exception {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(COMPANY);

        when(companyService.getCompanyDTO(anyLong(), anyLong())).thenReturn(companyDTO);

        //When & then
        mockMvc.perform(
                get(CompanyController.BASE_URL + "/" + ACCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString())
                        .content(asJsonString(companyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(COMPANY)));
    }
}