package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.SaleDTO;
import com.globalcrm.rest.domain.*;
import com.globalcrm.rest.repositories.SaleHistoryRepository;
import com.globalcrm.rest.repositories.SaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SaleServiceImplTest {

    public static final Long USER_ID = 223L;
    public static final Long SALE_ID = 3445L;
    public static final Long ACCOUNT_ID = 12454L;
    public static final Long CONTACT_ID = 33243L;
    public static final String TITLE = "TITLE";
    public static final String DESCR = "DESCR";
    SaleService saleService;

    @Mock
    UserService userService;
    @Mock
    ContactService contactService;
    @Mock
    SaleRepository saleRepository;
    @Mock
    SaleHistoryRepository saleHistoryRepository;
    @Mock
    AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        saleService = new SaleServiceImpl(userService, contactService, saleRepository, saleHistoryRepository, accountService);
    }

    @Test
    public void createNewSale() {
        User user = new User();
        user.setId(USER_ID);

        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setTitle(TITLE);
        sale.setDescription(DESCR);

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setTitle(TITLE);
        saleDTO.setDescription(DESCR);

        when(userService.findUserByAccountAndId(anyLong(), anyLong())).thenReturn(user);
        when(contactService.findById(anyLong())).thenReturn(new Contact());
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);
        SaleDTO savedSaleDTO = saleService.createNewSale(ACCOUNT_ID, USER_ID, CONTACT_ID, saleDTO);

        assertEquals(TITLE, savedSaleDTO.getTitle());
        assertEquals(DESCR, savedSaleDTO.getDescription());
    }

    @Test
    public void updateSaleStage() {

        Sale sale = new Sale();
        sale.setId(SALE_ID);
        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(sale));
        sale.setCurrentStage(SaleStage.CLOSED_LOST);
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);
        SaleDTO saved = saleService.updateSaleStage(SALE_ID, SaleStage.CLOSED_LOST);

        assertEquals(SaleStage.CLOSED_LOST, saved.getCurrentStage());
    }

    @Test
    public void getAllSalesByAccount() {
        Account account = new Account();
        account.setId(ACCOUNT_ID);

        User user = new User();
        user.getSales().add(new Sale());

        account.getUsers().add(user);
        when(accountService.findById(anyLong())).thenReturn(account);
        List<SaleDTO> salesDTOList = saleService.getAllSalesByAccount(ACCOUNT_ID);

        assertEquals(1, salesDTOList.size());
    }
}