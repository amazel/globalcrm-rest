package com.globalcrm.rest.controllers.v1;

import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.SaleStage;
import com.globalcrm.rest.exceptions.RestResponseEntityExceptionHandler;
import com.globalcrm.rest.services.v1.SaleService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SaleControllerTest {

    public static final Long ACCOUNT_ID = 123L;
    public static final Long USER_ID = 232L;
    public static final Long CONTACT_ID = 234L;
    private static final String TITLE = "Titulo";
    public static final String DESCRIPTION = "Sale description";
    public static final Long SALE_ID = 312L;
    @InjectMocks
    SaleController saleController;

    @Mock
    SaleService saleService;

    MockMvc mockMvc;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(saleController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void createNewSale() throws Exception{

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setTitle(TITLE);
        saleDTO.setDescription(DESCRIPTION);


        SaleDTO retSale = new SaleDTO();
        retSale.setTitle(TITLE);
        retSale.setDescription(DESCRIPTION);
        retSale.setId(SALE_ID);
        retSale.setCurrentStage(SaleStage.INITIAL_CONTACT);

        when(saleService.createNewSale(anyLong(),anyLong(), anyLong(),any(SaleDTO.class))).thenReturn(retSale);
        //When & then
        mockMvc.perform(
                post(SaleController.BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("accountId", ACCOUNT_ID.toString())
                        .param("userId", USER_ID.toString())
                        .param("contactId", CONTACT_ID.toString())
                        .content(asJsonString(saleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", equalTo(TITLE)))
                .andExpect(jsonPath("$.description", equalTo(DESCRIPTION)));
    }

    @Test
    public void getAllAccountSales() {
    }
}