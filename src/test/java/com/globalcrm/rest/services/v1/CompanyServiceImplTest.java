package com.globalcrm.rest.services.v1;

import com.globalcrm.rest.api.v1.model.CompanyDTO;
import com.globalcrm.rest.domain.Account;
import com.globalcrm.rest.domain.Company;
import com.globalcrm.rest.domain.VisibleFor;
import com.globalcrm.rest.repositories.AccountRepository;
import com.globalcrm.rest.repositories.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CompanyServiceImplTest {

    private static final String NAME = "Company Name";
    private static final String CITY = "CITY";
    private static final String ADDRESS = "ADDRESS";
    private static final String ZIP_CODE = "07890";
    private static final String STATE = "STATE";
    private static final Long ACCT_ID = 1L;
    private static final Long COMPANY_ID = 2L;

    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        companyService = new CompanyServiceImpl(companyRepository, accountRepository);
    }

    @Test
    public void createCompany() {
        Company company = new Company();
        company.setId(COMPANY_ID);
        company.setAddress(ADDRESS);
        company.setCity(CITY);
        company.setName(NAME);
        company.setState(STATE);
        company.setVisibleFor(VisibleFor.ALL);
        company.setZipCode(ZIP_CODE);

        Account account = new Account();
        account.setId(ACCT_ID);
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        account.getCompanies().add(company);

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(NAME);
        companyDTO.setCity(CITY);
        companyDTO.setName(NAME);
        companyDTO.setState(STATE);
        companyDTO.setVisibleFor(VisibleFor.ALL);
        companyDTO.setZipCode(ZIP_CODE);

        CompanyDTO companyRet = companyService.createCompany(ACCT_ID, companyDTO);

        //assertEquals(COMPANY_ID, companyRet.getId());
        assertEquals(CITY, companyRet.getCity());
        assertEquals(ADDRESS, companyRet.getAddress());
        assertEquals(NAME, companyRet.getName());
        assertEquals(STATE, companyRet.getState());
        verify(accountRepository, times(1)).save(any(Account.class));
    }


    @Test
    public void getAccountCompanyById() {

        Account account = new Account();
        account.setId(ACCT_ID);

        Company company = new Company();
        company.setId(COMPANY_ID);

        account.getCompanies().add(company);

        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        CompanyDTO companyDTO = companyService.getAccountCompanyById(ACCT_ID, COMPANY_ID);

        assertEquals(COMPANY_ID, companyDTO.getId());
        verify(accountRepository, times(1)).findById(anyLong());

    }
}