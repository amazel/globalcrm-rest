package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.api.v1.model.ContactDTO;
import com.globalcrm.rest.exceptions.RestResponseEntityExceptionHandler;
import com.globalcrm.rest.services.v1.CompanyService;
import com.globalcrm.rest.services.v1.ContactService;
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

public class ContactControllerTest {
    public static final Long ACCT_ID = 1L;
    public static final Long COMPANY_ID = 12L;
    public static final String NAMES = "Name1 Name2";

    @InjectMocks
    ContactController contactController;

    @Mock
    CompanyService companyService;

    @Mock
    ContactService contactService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getCompanyContacts() throws Exception {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.getContacts().add(new ContactDTO());

        when(companyService.getAccountCompanyById(anyLong(), anyLong())).thenReturn(companyDTO);

        mockMvc.perform(get(CompanyController.BASE_URL + "/" + ACCT_ID + "/companies/" + COMPANY_ID + "/contacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void createNewContact() throws Exception {

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(NAMES);

        when(contactService.createContact(anyLong(), anyLong(), any(ContactDTO.class))).thenReturn(contactDTO);

        //When & then
        mockMvc.perform(
                post(ContactController.BASE_URL + "/" + ACCT_ID + "/companies/" + COMPANY_ID + "/contacts/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contactDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.names", equalTo(NAMES)));
    }
}