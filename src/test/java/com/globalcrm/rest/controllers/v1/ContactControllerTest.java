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

import java.util.ArrayList;
import java.util.List;

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
    public static final Long CONTACT1_ID = 1212L;
    public static final Long COMPANY2_ID = 2345L;
    private static final Long CONTACT2_ID = 2323L;
    private static final String NAMES2 = "names3 names4";

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
    public void createNewContact() throws Exception {

        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setNames(NAMES);

        when(contactService.createContact(anyLong(), anyLong(), any(ContactDTO.class))).thenReturn(contactDTO);

        //When & then
        mockMvc.perform(
                post(ContactController.BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString())
                        .param("companyId", COMPANY_ID.toString())
                        .content(asJsonString(contactDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.names", equalTo(NAMES)));
    }

    @Test
    public void getContactsByAccountAndCompany() throws Exception {

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(COMPANY_ID);

        ContactDTO contactDTO1 = new ContactDTO();
        contactDTO1.setId(CONTACT1_ID);
        contactDTO1.setNames(NAMES);

        companyDTO.getContacts().add(contactDTO1);

        when(companyService.getCompanyByAccountAndId(anyLong(), anyLong())).thenReturn(companyDTO);
        //When & then
        mockMvc.perform(
                get(ContactController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString())
                        .param("companyId", COMPANY_ID.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getContactsByAccountAndNoCompany() throws Exception {

        List<ContactDTO> contactDTOList = new ArrayList<>();

        CompanyDTO company1DTO = new CompanyDTO();
        company1DTO.setId(COMPANY_ID);

        ContactDTO contactDTO1 = new ContactDTO();
        contactDTO1.setId(CONTACT1_ID);
        contactDTO1.setCompany(company1DTO);
        contactDTO1.setNames(NAMES);

        ContactDTO contactDTO2 = new ContactDTO();
        contactDTO2.setId(CONTACT2_ID);
        contactDTO2.setCompany(company1DTO);
        contactDTO2.setNames(NAMES2);

        contactDTOList.add(contactDTO1);
        contactDTOList.add(contactDTO2);

        when(contactService.getAllContactsByAccount(anyLong())).thenReturn(contactDTOList);
        //When & then
        mockMvc.perform(
                get(ContactController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCT_ID.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}